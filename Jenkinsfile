podTemplate(inheritFrom: 'basic', yaml: '''
spec:
  containers:
  - name: "jnlp"
''')
{
    node {
        properties([
            disableConcurrentBuilds(abortPrevious: true),
            buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '2', daysToKeepStr: '', numToKeepStr: '5')),
            gitLabConnection('gitlab.eclipse.org'),
            [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
            [$class: 'JobLocalConfiguration', changeReasonComment: '']
        ])

        deleteDir()

        stage('prepare') {
            dir('kura-wires') {
                checkout scm
            }
        }
        stage('Build kura-wires') {
            timeout(time: 2, unit: 'HOURS') {
                dir('kura-wires') {
                    withMaven(jdk: 'temurin-jdk17-latest', maven: 'apache-maven-3.9.6', options: [artifactsPublisher(disabled: true)]) {
                        sh 'mvn clean install'
                    }
                }
            }
        }
        stage('Archive .deb artifacts') {
	        dir("kura-wires") {
	            archiveArtifacts artifacts: '**/*.deb', onlyIfSuccessful: true
	        }
    	}
    }
}
