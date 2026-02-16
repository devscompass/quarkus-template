# Configure your environment: https://firebase.google.com/docs/studio/customize-workspace
{ pkgs, ... }: {
  channel = "stable-25.05";

  packages = [
    pkgs.jdk21
    pkgs.git
    pkgs.gnumake
    pkgs.docker
    pkgs.curl
    pkgs.jq
  ];

  services.docker.enable = true;

  env = {
    JAVA_HOME = "${pkgs.jdk21}/lib/openjdk";
    QUARKUS_PROFILE = "dev";
  };

  idx = {
    extensions = [
      "editorconfig.editorconfig"
      "esbenp.prettier-vscode"
      "josevseb.google-java-format-for-vs-code"
      "mkhl.shfmt"
      "pkief.material-icon-theme"
      "redhat.java"
      "redhat.vscode-microprofile"
      "redhat.vscode-quarkus"
      "redhat.vscode-xml"
      "redhat.vscode-yaml"
      "renesaarsoo.sql-formatter-vsc"
      "vscjava.vscode-java-debug"
      "vscjava.vscode-java-dependency"
      "vscjava.vscode-java-test"
      "vscjava.vscode-maven"
    ];

    previews = {
      enable = true;
      previews = {
        web = {
          command = ["./mvnw" "quarkus:dev"];
          manager = "web";
          env = {
            PORT = "$PORT";
            QUARKUS_HTTP_PORT = "$PORT";
            QUARKUS_PROFILE = "dev";
          };
        };
      };
    };

    workspace = {
      onCreate = {
        install-deps = "./mvnw dependency:resolve";
      };
      onStart = {
        quarkus-info = "./mvnw quarkus:info";
      };
    };
  };
}
