# TP Java avec MongoDB

## Prérequis

* Avoir préalablement installer Java
* Avoir préalablement installer Maven
* Avoir préalablement installer MongoDB

MongoDB utilise par défaut un répertoire pour macOS /data/db (il faudra donc le lancer avec les droits super-user) et pour Windows C:/data/db

Si vous ne souhaitez pas utiliser le repertoire par défaut il faudra donc utiliser la commande pour le lancement du serveur
> mongod --dbpath <chemin>

Pour ma part j'utilise le répertoire par défaut.


## Import des données de tests de MongoDB

Suivre les instructions de https://docs.mongodb.com/getting-started/shell/import-data/ pour importer la base des restaurants

Récupérer le JSON https://raw.githubusercontent.com/mongodb/docs-assets/primer-dataset/primer-dataset.json via 

> wget https://raw.githubusercontent.com/mongodb/docs-assets/primer-dataset/primer-dataset.json

Importer les données dans mongo

> mongoimport --db test --collection restaurants --drop --file ~/Downloads/primer-dataset.json

## Sujet de TP

Avec la base des Restaurants quelques requète via le mongo shell

Toujours avec la base des Restaurants des tests via Java avec JUnit


