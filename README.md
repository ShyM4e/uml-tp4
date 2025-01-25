
# Projet UML Generator

## Description du Projet
Ce projet est une application Java qui permet d'analyser la structure d'un projet Java en explorant ses packages, classes, méthodes, attributs et relations. 
Il génère également des fichiers XML et XMI pour représenter la structure du projet de manière standardisée, il offre aussi une interface graphique permettant aux utilisateurs de choisir un projet puis afficher son diagramme de classe.

## Fonctionnalités Principales
1. **Exploration des Packages** : Parcourt les packages d'un projet Java et liste les classes qu'ils contiennent.
2. **Analyse des Classes** : Extrait les informations sur les classes, y compris les attributs, les méthodes, les constructeurs et les annotations.
3. **Génération de XML** : Crée un fichier XML représentant la structure du projet.
4. **Génération de XMI** : Génère un fichier XMI (format standard pour les modèles UML) à partir de la structure du projet.
5. **Interface Graphique** : Une interface utilisateur pour explorer les fichiers, pourque l'utilisateur choisisse une projet pour le scanner et afficher son diagramme de classes.

## Classes Importantes

### `ClassParser`
- **Rôle** : Analyse une classe Java et extrait ses informations (attributs, méthodes, constructeurs, annotations, etc.).
- **Méthodes Clés** :
  - `getClassInfo()` : Retourne les informations de base de la classe (nom, modificateurs).
  - `getClassFields()` : Retourne la liste des attributs de la classe.
  - `getClassMethods()` : Retourne la liste des méthodes de la classe.
  - `extractRelationships()` : Identifie les relations entre les classes (héritage, agrégation, etc.).

### `PackageExplorer`
- **Rôle** : Explore les packages d'un projet et liste les classes qu'ils contiennent.
- **Méthodes Clés** :
  - `scan()` : Parcourt le répertoire source pour identifier les packages et les classes.

### `ProjectParser`
- **Rôle** : Analyse un projet entier en utilisant `PackageExplorer` et `ClassParser`.
- **Méthodes Clés** :
  - `parseProject()` : Retourne un objet `ProjectFormat` contenant la structure complète du projet.

### `XMLTransformer` et `XMITransformer`
- **Rôle** : Transforme la structure du projet en fichiers XML et XMI.
- **Méthodes Clés** :
  - `transformToXML()` : Génère un fichier XML à partir de la structure du projet.
  - `transformToXMI()` : Génère un fichier XMI à partir de la structure du projet.

## Comment Utiliser le Projet
  Exécutez la classe `Examples` pour tester les différentes fonctionnalités.

## Exemples de Tests
La classe `Examples` contient plusieurs méthodes pour tester les fonctionnalités du projet :
- `exp01()` : Analyse un projet et affiche sa structure dans la console.
- `exp02()` : Génère un fichier XML à partir de la structure du projet.
- `exp03()` : Génère un fichier XMI à partir de la structure du projet.
- `exp04()` : Lit un fichier XML et affiche la structure du projet.
- `exp05()` : Lance l'interface graphique pour visualiser le diagramme UML du projet choisi.

## Auteur
- **Chaymae EL BOUSSADANY**
