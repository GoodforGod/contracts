# Galaxy Guardians Contracts

PaaS for contracts for galaxy guardians squad.

## Build & Run locally

Steps to do:
* Install Oracle Database or docker image (for *Aggregator and Oracle provider*).
* Download repository.
* Download [oJDBC (ojdbc7.jar)](https://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html)
* Rename file to *ojdbc7-12.1.0.2.jar*
* Create **driver** folder inside *project home directory*
* Put renamed oracle database driver in those folder

Build projects with:
```bash
./gradlew bootJar
```

Run project with:
```bash
./gradlew bootRun
```