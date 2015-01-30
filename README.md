Projet Graphique Tamagoshi
==========================

## Gestion du cycle de vie d'une partie
L'interface Observer et la classe Observable ont été utilisées pour gérer le cycle de vie d'une partie. Ainsi, lorsque le joueur appuie sur une des commandes "nourrir" ou "jouer", une notification est lancée à toutes les frames (toutes les TamasFrames s'écoutent elles-même) et s'*updatent* en désactivant la possibilité au joueur de lancer la **même action** sur les autres tamagoshis de la partie en cours.

Quand le joueur a nourri un tamagoshi et a joué avec le même ou un autre, la fenêtre principale fait passer un tour et assure l'anthropie des tamagoshis (la Frame principale écoutant aussi toutes les frames qu'elle a lancé)


## Création de l'interface graphique
Une grande partie de l'interface graphique a été gérée avec le plugin eclipse (installé par défaut dans les dernières versions) Window Builder

### Dessin d'un tamagoshi
Pour dessiner un tamagoshi, le package dessin créé précédemment en TP a été utilisé.

__git repository : https://github.com/monzey/tamagoshi.git__
