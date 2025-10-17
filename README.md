Le client saisit une opération arithmétique au format : `10 + 5`, `-20 * 3`
- Validation côté client avec expression régulière → rejette les entrées invalides (`abc + 3`, `25 ** 55`, etc.).
- Prise en charge des opérations : `+`, `-`, `*`, `/` (avec gestion de la division par zéro).
- Saisie de `0` → arrêt propre de la communication.
- **Partie 2-1** : Envoi via `DataInputStream` / `DataOutputStream`.
- **Partie 2-2** : Envoi via **objets sérialisés** (`ObjectOutputStream` / `ObjectInputStream`) avec la classe `Operation implements Serializable`.
