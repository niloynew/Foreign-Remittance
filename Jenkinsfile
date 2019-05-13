pipeline {
  agent any
  
  environment {
            ARTIFACT_NAME="ababil-foreign-remittance"
            ARTIFACT_MODULE="${ARTIFACT_NAME}-rs"
            ARTIFACT_SERVICE="${ARTIFACT_NAME}-service"
      }
      
  stages {
    stage('Build') {
      agent {
        docker {
          image 'mislbd/oracle-java:8u66'
        }

      }
      steps {
        sh './gradlew clean build -x test'
        sh 'echo "ababil-foreign-remittance-rs/build/libs/$(cat gradle.properties | grep PROJECT_ARTIFACT | cut -d\'=\' -f2-)-$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-).jar" > ababil-foreign-remittance-rs/build/artifact'
        sh 'echo "$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-)" > ababil-foreign-remittance-rs/build/version'
        stash(includes: 'ababil-foreign-remittance-rs/build/', name: 'dist')
      }
    }
    stage('Docker Image') {
      steps {
        unstash 'dist'
        sh 'docker build --rm --force-rm --build-arg BUILD_ID="$BUILD_ID" --build-arg ARTIFACT="$(cat ababil-foreign-remittance-rs/build/artifact)" --build-arg VERSION="$(cat ababil-foreign-remittance-rs/build/version)" -t mislbd/ababil-foreign-remittance-service .'
        sh 'docker tag mislbd/ababil-foreign-remittance-service 172.16.190.13:5000/mislbd/ababil-foreign-remittance-service'
        sh 'docker tag mislbd/ababil-foreign-remittance-service 172.16.190.13:5000/mislbd/ababil-foreign-remittance-service:$(cat ababil-foreign-remittance-rs/build/version)-B$BUILD_ID'
      }
    }
    stage('Push Image') {
      steps {
        unstash 'dist'
        sh 'docker push 172.16.190.13:5000/mislbd/ababil-foreign-remittance-service'
        sh 'docker push 172.16.190.13:5000/mislbd/ababil-foreign-remittance-service:$(cat ababil-foreign-remittance-rs/build/version)-B$BUILD_ID'
      }
    }
  }
}

