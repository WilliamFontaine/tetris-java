# Tetris_JavaFx

Notre jeu “LéCubKiTomb” reprend le principe du très célèbre jeu Tetris. Notre application vous propose de jouer très facilement à ce jeu en un clic seulement !  
Notre jeu propose quelques fonctionnalités en plus, comparé à un Tetris “classique”. Vous avez la possibilité de changer les touches de jeu, les touches permettant de faire glisser, tourner ou accélérer des pièces.  
Notre jeu propose aussi une gestion de la taille de la grille pour que la difficulté soit modulaire. Un slider est disponible dans la page d’options, ce qui vous permet de modifier la difficulté en fonction de vos envies.  
Vous pouvez aussi consulter votre score en fin de partie mais vous apparaitrez dedans seulement si vous faites partie de l’élite des joueurs !  



L’application respecte la “Separation of Concerns” (SoC). C’est un concept de conception visant à séparer un programme informatique en plusieurs parties. Chacune des parties doit donc gérer un aspect, une partie de l’application. C’est une bonne pratique qui permet de segmenter le code et de le rendre plus simple pour notamment la maintenance mais aussi le débogage.  
Plus largement, cela permet de donner une responsabilité à un “projet”.  


Dans notre application, la partie fonctionnelle de jeu est donc codée en Java mais le Java ne peut pas s’occuper de la partie visuelle. Il est doté d'une partie “visuelle” très simple en console mais cette partie se limite ici. Le jeu doit intégralement fonctionner en mémoire sans que la partie visuelle soit présente.  
La partie graphique est donc quant à elle codée en JavaFX. Elle vient se superposer à la partie Java. C’est une sorte de surcouche permettant d’avoir d’une part un aspect graphique pour notre jeu mais aussi de modifier certains paramètres comme les touches de jeu, la taille de la grille, etc.  
Le jeu est relié avec l’affichage pour que ces deux concepts soient synchronisés.  
