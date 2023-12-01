# e-banking-spring-backend
## Telecharger Limage Sonarqube dans docker:
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9001:9000 sonarqube:latest
![sonarqube apartir docker](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/dfda760a-877a-4233-904d-8a8c9ce6b4b6)
Image Sonarqube disponible sur mon docker
![docker images](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/23efaa0c-eede-49ff-9d5d-b6ccb98c4faf)
Interface Sonarqube 
![inter](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/4e912a5c-252f-42b8-82e8-8f29e4771270)

code de mon github/workflows/build.yml

![build](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/52106ba4-8a20-4145-8057-fd5dbecaee3b)

## plugin sonar a mettre dans pom.xml

![plugins sonar](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/525d5dd1-c432-4ba9-a11c-acaf42145bcd)

## les secret SONARQUBE_HOST ET  SONARQUBE_TOKEN NE PAS OUBLIER
     
- [SONARQUBE_HOST ](#SONARQUBE_HOST )
  On utilise ngrok pour generer une lien vers mon compte sonar:
  sur ngrok on tape :ngrok.exe http 9001
   https://5a3f-154-125-199-254.ngrok-free.app -> http://localhost:9001  
  ![ngrok](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/ea6aef88-2700-4593-adea-480090ca97dc)
- [SONARQUBE_TOKEN](#SONARQUBE_TOKENT )
  on le genere apartir de la creation de mon projet au iveau d esonarqube
  #GITHUB_ACTION_SCAN     sqp_90ecfb99683410e9deb5e33e1f296451b7d94a0c

  [GITHUB_ACTION_SCAN ](#GITHUB_ACTION_SCAN )
  On cree deux repository secret
  ![github action](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/a04b8e40-6973-4999-b91c-594b3324b606)

- [Execution du comande generer ](#features)
  mvn clean verify sonar:sonar \
  -Dsonar.projectKey=GITHUB_ACTION_SCAN \
  -Dsonar.projectName='GITHUB_ACTION_SCAN' \
  -Dsonar.host.url=https://5a3f-154-125-199-254.ngrok-free.app \
  -Dsonar.token=sqp_90ecfb99683410e9deb5e33e1f296451b7d94a0c
  
- [resultat de mon projet E-banking](#getting-started) 

![1 res](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/f82db9a7-a4f7-45a3-abca-e664c13e16ea)
![2 res](https://github.com/youngerdiarrandiaye/e-banking-spring-backend/assets/122989242/dffc9aeb-170d-4e62-be60-b072b8c00c43)

GITHUB_ACTION_SCAN     sqp_90ecfb99683410e9deb5e33e1f296451b7d94a0c

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=GITHUB_ACTION_SCAN \
  -Dsonar.projectName='GITHUB_ACTION_SCAN' \
  -Dsonar.host.url=https://5a3f-154-125-199-254.ngrok-free.app \
  -Dsonar.token=sqp_90ecfb99683410e9deb5e33e1f296451b7d94a0c
```bash
# Example command or code snippet
