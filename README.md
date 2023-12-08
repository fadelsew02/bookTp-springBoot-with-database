# Objectif du TP
    Créer un service RESTful simple avec Spring Boot pour gérer une liste de livres. avec base de données postgres

### La partie 1 de ce TP se trouve à l'adresse https://github.com/fadelsew02/bookTp-spring-boot


## Migration vers MySQL

Si vous souhaitez utiliser MySQL au lieu de PostgreSQL, suivez ces étapes :

1. **Modifier le fichier `pom.xml` :**
   - Remplacez la dépendance PostgreSQL par la dépendance MySQL.

     Exemple :
     ```xml
     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <scope>runtime</scope>
     </dependency>
     ```

2. **Modifier le fichier `src/main/resources/application.properties` :**
   - Modifiez les paramètres de configuration de la base de données pour correspondre à MySQL.

     Exemple :
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.datasource.username=utilisateur
     spring.datasource.password=mot_de_passe
     ```

3. **Exécuter le projet avec MySQL :**
   - Lancez votre projet Spring Boot avec la nouvelle configuration.

N'oubliez pas de remplacer `nom_de_la_base`, `utilisateur`, et `mot_de_passe` par les valeurs appropriées. 
