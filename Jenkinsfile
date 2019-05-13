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
        sh 'sh ./gradlew clean build -x test'
        sh 'echo "${ARTIFACT_MODULE}/build/libs/$(cat gradle.properties | grep PROJECT_ARTIFACT | cut -d\'=\' -f2-)-$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-).jar" > ${ARTIFACT_MODULE}/build/artifact'
        sh 'echo "$(cat gradle.properties | grep PROJECT_VERSION | cut -d\'=\' -f2-)" > ${ARTIFACT_MODULE}/build/version'
        stash(includes: 'ababil-foreign-remittance-rs/build/', name: 'dist')
      }
    }
    stage('Docker Image') {
      steps {
        unstash 'dist'
        sh 'docker build --rm --force-rm --build-arg BUILD_ID="$BUILD_ID" --build-arg ARTIFACT="$(cat ${ARTIFACT_MODULE}/build/artifact)" --build-arg VERSION="$(cat ${ARTIFACT_MODULE}/build/version)" -t mislbd/${ARTIFACT_NAME}-service .'
        sh 'docker tag mislbd/${ARTIFACT_NAME}-service 172.16.190.13:5000/mislbd/${ARTIFACT_NAME}-service'
        sh 'docker tag mislbd/${ARTIFACT_NAME}-service 172.16.190.13:5000/mislbd/${ARTIFACT_NAME}-service:$(cat ${ARTIFACT_MODULE}/build/version)-B$BUILD_ID'
      }
    }
    stage('Push Image') {
      steps {
        unstash 'dist'
        sh 'docker push 172.16.190.13:5000/mislbd/${ARTIFACT_NAME}-service'
        sh 'docker push 172.16.190.13:5000/mislbd/${ARTIFACT_NAME}-service:$(cat ${ARTIFACT_MODULE}/build/version)-B$BUILD_ID'
      }
    }
  }
  environment {
    ARTIFACT_NAME = 'foreign-remittance'
    ARTIFACT_MODULE = "${ARTIFACT_NAME}-rs"
  }
}