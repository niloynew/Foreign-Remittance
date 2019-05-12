pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'mislbd/oracle-java:8u66'
        }

      }
      steps {
        sh './gradlew clean build -x test'
        sh 'echo "ababil-remittance-rs/build/libs/$(cat gradle.properties | grep PROJECT_ARTIFACT | cut -d\'=\' -f2-)-$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-).jar" > ababil-remittance-rs/build/artifact'
        sh 'echo "$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-)" > ababil-remittance-rs/build/version'
        stash(includes: 'ababil-remittance-rs/build/', name: 'dist')
      }
    }
    stage('Docker Image') {
      steps {
        unstash 'dist'
        sh 'docker build --rm --force-rm --build-arg BUILD_ID="$BUILD_ID" --build-arg ARTIFACT="$(cat ababil-remittance-rs/build/artifact)" --build-arg VERSION="$(cat ababil-remittance-rs/build/version)" -t mislbd/ababil-remittance-service .'
        sh 'docker tag mislbd/ababil-remittance-service 172.16.190.13:5000/mislbd/ababil-remittance-service'
        sh 'docker tag mislbd/ababil-remittance-service 172.16.190.13:5000/mislbd/ababil-remittance-service:$(cat ababil-remittance-rs/build/version)-B$BUILD_ID'
      }
    }
    stage('Push Image') {
      steps {
        unstash 'dist'
        sh 'docker push 172.16.190.13:5000/mislbd/ababil-remittance-service'
        sh 'docker push 172.16.190.13:5000/mislbd/ababil-remittance-service:$(cat ababil-remittance-rs/build/version)-B$BUILD_ID'
      }
    }
  }
}