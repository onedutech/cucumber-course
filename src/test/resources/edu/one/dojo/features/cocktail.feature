# language: fr
Fonctionnalité: : Cocktail Ordering
  En tant que Romeo, I want to offer a drink to Juliette so that we can discuss together (and maybe more).

  @order @cocktail
  Plan du Scénario: Creating an empty order
    Etant donné que <from> who wants to buy a drink
    Quand an order is declared for <to>
    Alors there is <nbCocktails> cocktails in the order

    Exemples:
      | from  | to       | nbCocktails |
      | Romeo | Juliette | 0           |
      | Tom   | Jerry    | 0           |

  @message @chat
  Plan du Scénario: Sending a message with an order
    Etant donné que <from> who wants to buy a drink
    Quand an order is declared for <to>
    Et a message saying "<message>" is added
    Alors the ticket must say "<expected>"

    Exemples: :
  | from  | to       | message     | expected                            |
  | Romeo | Juliette | Wanna chat? | From Romeo to Juliette: Wanna chat? |
  | Tom   | Jerry    | Hei!        | From Tom to Jerry: Hei!             |
