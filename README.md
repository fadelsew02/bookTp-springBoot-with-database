# Objectif du TP
    Créer un service RESTful simple avec Spring Boot pour gérer une liste de livres.

# GetMapping

## @PathVariable Long bookId :
    Cette annotation indique que la valeur de la variable bookId est extraite de la partie de l'URL correspondant à la variable de chemin {bookId}.

## books.stream() :
    Cela crée un flux (stream) à partir de la liste books. Un flux est une séquence d'éléments sur laquelle des opérations peuvent être effectuées.

## .filter(book -> book.getId().equals(bookId)) :
    Cela filtre le flux en utilisant la condition fournie dans la lambda expression. Il ne laisse passer que les éléments dont l'ID correspond à bookId.

## .findFirst() :
    Cela retourne le premier élément qui correspond à la condition du filtre. Si aucun élément ne correspond, il retourne un objet Optional vide.

## .orElse(null) :
    Cela récupère la valeur à l'intérieur de l'Optional. S'il y a un élément qui correspondait à la condition du filtre, il retourne cet élément. Sinon, il retourne null.

En résumé, cette fonction cherche un livre dans la liste books dont l'ID correspond à la valeur fournie dans la variable de chemin bookId. Si un tel livre est trouvé, il est renvoyé. Sinon, null est renvoyé. Notez que l'utilisation de orElse(null) suggère que la fonction est conçue pour être appelée dans un contexte où le résultat peut ne pas être présent, d'où l'utilisation d'un Optional.


----------------------------------------------------------------------------------------
La méthode createBook est destinée à être utilisée en réponse à une requête HTTP de type POST, ce qui signifie qu'elle est conçue pour créer un nouveau livre dans l'application. Voici une explication détaillée de ce que fait cette méthode :

# @PostMapping :
    Cette annotation indique que cette méthode gère les requêtes HTTP de type POST.

## public Book createBook(@RequestBody Book newBook) :
    La méthode prend en paramètre un objet Book qui représente les données du nouveau livre à créer. L'annotation @RequestBody indique que les données du livre seront extraites du corps de la requête HTTP.

## newBook.setId((long) (books.size() + 1)) 
    Cette ligne attribue un nouvel ID au livre en fonction de la taille actuelle de la liste books. Cela suppose que les ID des livres sont incrémentés de manière séquentielle.

## books.add(newBook) 
    Le nouveau livre est ajouté à la liste books. Cette liste semble être une liste globale qui contient tous les livres créés jusqu'à présent dans l'application.

## return newBook :
    Une fois le livre créé et ajouté à la liste, la méthode renvoie l'objet newBook. Cela peut être utile du côté client pour obtenir l'ID généré ou toute autre modification apportée au livre lors de sa création.

En résumé, cette méthode gère la création d'un nouveau livre. Elle attribue un nouvel ID au livre, l'ajoute à une liste de livres (ou à une autre structure de données similaire), puis renvoie le livre nouvellement créé. Notez que cette méthode suppose une approche simple pour la génération des ID et ne traite pas d'aspects tels que la validation des données du livre.

----------------------------------------------------------------------------------------------

# @PutMapping("/{bookId}") :
    Cette annotation indique que cette méthode gère les requêtes HTTP de type PUT pour l'URL spécifiée, où {bookId} est une variable de chemin représentant l'ID du livre à mettre à jour.

## public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) :
    La méthode prend en paramètre l'ID du livre à mettre à jour (bookId) extrait de la variable de chemin, et un objet Book (updatedBook) représentant les nouvelles données du livre extraites du corps de la requête HTTP (indiqué par @RequestBody).

## for (int i = 0; i < books.size(); i++) { :
    La boucle parcourt la liste des livres pour trouver le livre avec l'ID correspondant à celui fourni dans la variable de chemin (bookId).
    
## Book existingBook = books.get(i); :
    À chaque itération de la boucle, un livre existant est extrait de la liste.

## if (existingBook.getId().equals(bookId)) { :
    On vérifie si l'ID du livre existant correspond à l'ID fourni dans la variable de chemin.

## updatedBook.setId(bookId); :
Si le livre existant est trouvé, l'ID du livre mis à jour est défini sur l'ID fourni dans la variable de chemin. Cela garantit que l'ID du livre ne change pas pendant la mise à jour.

## books.set(i, updatedBook); :
    Le livre existant dans la liste est remplacé par le livre mis à jour.

## return updatedBook; :
    La méthode renvoie le livre mis à jour, indiquant ainsi que la mise à jour a été effectuée avec succès.

## return null; :
    Si la boucle parcourt toute la liste sans trouver de livre correspondant à l'ID fourni, la méthode renvoie null pour indiquer qu'aucune mise à jour n'a été effectuée.

----------------------------------------------------------------------------------------------

# @DeleteMapping("/{bookId}") :
    Cette annotation indique que cette méthode gère les requêtes HTTP de type DELETE pour l'URL spécifiée, où {bookId} est une variable de chemin représentant l'ID du livre à supprimer.

## public void deleteBook(@PathVariable Long bookId) :
    La méthode prend en paramètre l'ID du livre à supprimer (bookId) extrait de la variable de chemin.

## books.removeIf(book -> book.getId().equals(bookId)); :
    Cette ligne utilise la méthode removeIf de la liste books pour supprimer tous les éléments qui correspondent à la condition fournie. Dans ce cas, elle supprime tous les livres dont l'ID correspond à l'ID fourni dans la variable de chemin.

En résumé, cette méthode cherche tous les livres dans la liste avec l'ID spécifié et les supprime. Elle utilise la méthode removeIf avec une expression lambda pour déterminer les livres à supprimer en fonction de leur ID. Ainsi, après l'exécution de cette méthode, les livres ayant l'ID spécifié sont retirés de la liste books.

--------------------------------------------------------------------------------------------

Dans la classe BookController que vous avez fournie, chaque méthode correspond à un endpoint particulier avec une route spécifique. Voici la correspondance entre chaque méthode et la route associée :

# @GetMapping - Endpoint pour récupérer tous les livres :

Verbe HTTP : GET
Route : /books
Méthode : getAllBooks


# @GetMapping("/{bookId}") - Endpoint pour récupérer un livre par son ID :

Verbe HTTP : GET
Route : /books/{bookId}
Méthode : getBookById


# @PostMapping - Endpoint pour créer un nouveau livre :

Verbe HTTP : POST
Route : /books
Méthode : createBook


# @PutMapping("/{bookId}") - Endpoint pour mettre à jour un livre existant :

Verbe HTTP : PUT
Route : /books/{bookId}
Méthode : updateBook


# @DeleteMapping("/{bookId}") - Endpoint pour supprimer un livre :

Verbe HTTP : DELETE
Route : /books/{bookId}
Méthode : deleteBook