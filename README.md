# Mein Trainingsbuch – Android-Projekt

Offline-Trainingsprotokoll für Kieser und weitere Studios (z. B. Superfit).

## Bauen

1. Ordner `KieserAndroid` in Android Studio öffnen.
2. Gradle-Synchronisierung mit Internetverbindung durchführen (Android Gradle Plugin 8.7.3; Android SDK 35).
3. Menü **Build > Build APK(s)** verwenden.
4. APK auf das eigene Android-Gerät übertragen und installieren.

Die Anwendung benötigt keine Internetberechtigung. Daten werden im WebView-Datenspeicher der App gehalten. Unter **Sicherung** regelmäßig eine JSON-Sicherung über die Android-Dateiauswahl speichern. Die CSV-Ausgabe dient der Tabellenanalyse.

## Funktionsumfang

- 10 vorausgefüllte Kieser-Übungen samt Einstellungen und Satzanzahlen
- Eigene Übungen hinzufügen, bearbeiten, entfernen und Reihenfolge ändern
- Gewichte/Wiederholungen pro Satz, Entwurfsspeicherung, Verlauf
- JSON-Backup/Restore und CSV-Export
- Offline-Nutzung ohne Konto

## Einschränkungen

- Noch keine fertig kompilierte APK: In dieser Laufzeitumgebung fehlen Android SDK und Gradle.
- Daten sind an diese App-Installation gebunden; bei Deinstallation kann der lokale Speicher verloren gehen.
- Einheiten sind aktuell löschbar, aber nicht nachträglich editierbar.
- Datenexport bei Android über den System-Dateidialog; JSON-Import über die Dateiauswahl.
