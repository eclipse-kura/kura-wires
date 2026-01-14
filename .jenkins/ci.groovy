@Library('add-ons-shared-libs@develop') _

node {
    continuousIntegrationPipeline(
        buildType: "deploy",
        sonar: [
            enable: true,
            projectKey: "eclipse-kura_kura-wires",
            tokenId: "sonarcloud-token-kura-wires",
            exclusions: "**/*.xml,**/*.yml",
            testExclusions: "**/*"
        ],
    )
}
