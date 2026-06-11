# Exercice BDD : Système de Gestion Bancaire (Retraits & Virements)

## Contexte du projet
On vous confie la responsabilité de spécifier et de concevoir les tests d'acceptation pour deux fonctionnalités critiques : **le retrait d'argent au distributeur (ATM)** et **le virement de compte à compte**. L'architecture de l'application impose des règles métiers strictes pour garantir la sécurité des transactions et éviter les découverts non autorisés.

---

## Règles Métier à Implémenter

### Feature 1 : Retrait au distributeur (ATM Withdrawal)
* **Règle 1 (Solde) :** Un client ne peut retirer de l'argent que si le solde de son compte est supérieur ou égal au montant demandé.
* **Règle 2 (Réserve ATM) :** Le distributeur possède sa propre réserve de billets. Si le distributeur n'a pas assez d'argent, la transaction est refusée, même si le client possède les fonds nécessaires.
* **Règle 3 (Impact) :** En cas de succès, le solde du client est débité et la réserve du distributeur est diminuée du montant exact.

### Feature 2 : Virement de compte à compte (Account Transfer)
* **Règle 1 (Acteurs) :** Un client peut transférer de l'argent de son compte (Débité) vers le compte d'un bénéficiaire (Crédité).
* **Règle 2 (Frais) :** Des frais de transfert fixes s'appliquent selon le type de compte d'origine (ex: 1€ pour un Compte Courant, 0€ pour un Compte Épargne).
* **Règle 3 (Éligibilité) :** Le virement n'est exécuté que si le solde du compte émetteur, après déduction du montant et des frais, reste supérieur ou égal à zéro.

---

## Travail Demandé

### Question 1 : Robustesse & Scenario Outline (Feature Retrait)
Rédigez un **Scenario Outline** (Plan du scénario) permettant de tester la robustesse de la fonctionnalité de retrait face aux cas d'échecs et de succès.
* Utilisez un tableau d'**Examples** (Exemples) pour valider au moins trois cas de figure :
  1. Un retrait réussi (solde suffisant, ATM suffisant).
  2. Un échec pour solde insuffisant.
  3. Un échec pour réserve ATM insuffisante.

### Question 2 : Gestion Multi-comptes & DataTables (Feature Virement)
Rédigez le scénario Gherkin complet pour la fonctionnalité de **Virement entre deux comptes**.
* Utilisez une **DataTable** pour décrire l'état initial des deux comptes (Émetteur et Bénéficiaire) incluant leurs soldes et leurs types de compte respectifs.
* Vérifiez l'état final précis des deux comptes après l'exécution du virement dans le bloc `Then` (Alors) en tenant compte des frais de transaction.

### Question 3 : Conception des Step Definitions 
Pour les deux scénarios générez le code des **Step Definitions* et le code de traitement à appeler. 
* Les expressions régulières ou Cucumber Expressions doivent être explicites.
* Récupérez les données de la **DataTable** 
* Implémenter le code source relatif au traitement fonctionnel de ces deux scénarios. Aucune Règle de gestion fonctionnel ne doit apparaitre dans les steps definition
