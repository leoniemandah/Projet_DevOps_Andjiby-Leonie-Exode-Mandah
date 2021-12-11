#!groovy
println('------------------------------------------------------------------Import Job CI/Job1')
def pipelineScript = new File('/var/jenkins_config/jobs/job1-pipeline.groovy').getText("UTF-8")

pipelineJob('CI/Job1') {
    description("Build .jar from job1 java application")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description("branch to push")
            trim(false)
        }
        booleanParam {
            name('SKIP_TESTS')
            defaultValue(true)
            description("paramètre pour passer les tests")
        }
          choice {
            name('VERSION_TYPE')
            choices(['SNAPSHOT', 'RELEASE'])
            description('Version type between snapshot and release')
        }
        stringParam {
            name('VERSION_TYPE')
            defaultValue('1.0')
            description("version")
            trim(false)
        }
      
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}
