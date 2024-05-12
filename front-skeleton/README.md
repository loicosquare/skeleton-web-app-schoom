# FrontSkeleton

## Les installations nécessaire
- Node JS : https://nodejs.org/en/download
- Angular : `npm install -g @angular/cli`

## Avant de lancer le projet

Lancer `npm i`

## Pour lancer le projet

Lancer `npm start` et se rendre sur `http://localhost:4200/`

## Explorons les variables d’environnement du Dockerfile:

- APP_NAME: Le nom de votre application Angular.
- ROUTING: Indique si le routing Angular est activé (true/false).
- STANDALONE: Détermine si le projet est en standalone (true/false).
- COPY : Copie les fichiers de l'application Angular dans le conteneur Docker (true/false).
- STRICT: Active le mode strict d'Angular (true/false).
- STYLE: Précise le préprocesseur de style à utiliser, par exemple, scss par défaut. 

## Explorons la ligne CMD:

- ng new $APP_NAME --routing=$ROUTING --standalone=$STANDALONE --strict=$STRICT --style=$STYLE : Cette partie crée un nouveau projet Angular avec des options spécifiées telles que le routage, le mode standalone, le mode strict et le style.
- mv $APP_NAME/* . : Déplace tous les fichiers et répertoires du nouveau répertoire de projet Angular vers le répertoire de travail actuel.
- rm -rf $APP_NAME : Supprime le répertoire vide restant après le déplacement des fichiers.
- ng serve --host 0.0.0.0 --port 4200 : Lance le serveur de développement Angular, servant l'application sur 0.0.0.0 et le port 4200.
