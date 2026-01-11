#!/bin/bash

# prompt.sh - Script pour gérer le projet Airline Management

# Couleurs pour l'affichage
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
PURPLE='\033[0;35m'
NC='\033[0m' # No Color

# Répertoires
PROJECT_DIR="."
TARGET_DIR="target"
SRC_DIR="src/main"
RESOURCES_DIR="src/main/resources"
WEBAPP_DIR="src/main/webapp"
STRUCTURE_DIR="structure"

# Fonction d'affichage
print_header() {
    echo -e "${PURPLE}"
    echo "========================================"
    echo "   AIRLINE MANAGEMENT - Gestionnaire   "
    echo "========================================"
    echo -e "${NC}"
}

print_menu() {
    echo -e "${YELLOW}Options disponibles:${NC}"
    echo -e "  ${GREEN}1${NC} - Lancer l'application (Maven)"
    echo -e "  ${GREEN}2${NC} - Compiler le projet (Maven)"
    echo -e "  ${GREEN}3${NC} - Nettoyer le projet (Maven clean)"
    echo -e "  ${GREEN}4${NC} - Tester PostgreSQL"
    echo -e "  ${GREEN}5${NC} - Lister tous les fichiers"
    echo -e "  ${GREEN}6${NC} - Afficher la structure du projet"
    echo -e "  ${GREEN}7${NC} - Créer source.txt avec code source"
    echo -e "  ${GREEN}8${NC} - Vérifier les dépendances Maven"
    echo -e "  ${GREEN}9${NC} - Redémarrer PostgreSQL"
    echo -e "  ${GREEN}10${NC} - Vérifier l'état des services"
    echo -e "  ${GREEN}11${NC} - Générer la documentation"
    echo -e "  ${GREEN}12${NC} - Exécuter les tests"
    echo -e "  ${GREEN}0${NC} - Quitter"
    echo -e "${YELLOW}----------------------------------------${NC}"
}

# Fonction pour lancer l'application
run_application() {
    echo -e "${BLUE}Lancement de l'application Spring Boot...${NC}"
    
    # Vérifier si Maven est installé
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        echo "Installez-le avec: sudo apt install maven"
        return 1
    fi
    
    # Exécuter l'application
    echo -e "${CYAN}Exécution de: mvn spring-boot:run${NC}"
    mvn spring-boot:run
}

# Fonction pour compiler
compile_project() {
    echo -e "${BLUE}Compilation du projet avec Maven...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        return 1
    fi
    
    echo -e "${CYAN}Exécution de: mvn compile${NC}"
    mvn compile
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Compilation réussie${NC}"
    else
        echo -e "${RED}✗ Erreurs de compilation${NC}"
    fi
}

# Fonction pour nettoyer
clean_project() {
    echo -e "${BLUE}Nettoyage du projet...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        return 1
    fi
    
    echo -e "${CYAN}Exécution de: mvn clean${NC}"
    mvn clean
    
    if [ -d "$TARGET_DIR" ]; then
        rm -rf $TARGET_DIR/*
        echo -e "${GREEN}✓ Répertoire target vidé${NC}"
    fi
    
    # Nettoyer d'autres fichiers temporaires
    rm -f source.txt error.log 2>/dev/null
    echo -e "${GREEN}✓ Fichiers temporaires supprimés${NC}"
}

# Fonction pour tester PostgreSQL
test_postgres() {
    echo -e "${BLUE}Test de connexion PostgreSQL...${NC}"
    
    # Vérifier si PostgreSQL est installé
    if ! command -v psql &> /dev/null; then
        echo -e "${RED}PostgreSQL n'est pas installé!${NC}"
        echo "Installez-le avec: sudo apt install postgresql postgresql-contrib"
        return 1
    fi
    
    # Vérifier le service
    echo -e "${CYAN}État du service PostgreSQL:${NC}"
    sudo systemctl status postgresql --no-pager | head -10
    
    # Tester la connexion
    echo -e "\n${CYAN}Test de connexion à la base de données:${NC}"
    if sudo -u postgres psql -c "\l" 2>/dev/null; then
        echo -e "${GREEN}✓ Connexion PostgreSQL réussie${NC}"
    else
        echo -e "${RED}✗ Échec de connexion PostgreSQL${NC}"
        echo -e "${YELLOW}Conseil: Essayez de réinitialiser le mot de passe:${NC}"
        echo "  sudo -u postgres psql -c \"ALTER USER postgres WITH PASSWORD 'postgres';\""
    fi
    
    # Afficher les bases de données
    echo -e "\n${CYAN}Bases de données disponibles:${NC}"
    sudo -u postgres psql -c "\l" 2>/dev/null | grep -E "(postgres|airline|Name)"
}

# Fonction pour lister tous les fichiers
list_all_files() {
    echo -e "${BLUE}Liste de tous les fichiers (sauf target):${NC}"
    echo -e "${CYAN}"
    
    # Trouver et afficher tous les fichiers pertinents
    find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.properties" -o -name "*.sql" -o -name "*.jsp" -o -name "*.css" -o -name "*.sh" -o -name "*.txt" -o -name "*.md" \) \
        -not -path "./target/*" \
        -not -path "./.git/*" \
        -not -name "*.class" \
        | sort | while read file; do
        echo "  ${file:2}"
    done
    
    # Compter par type
    echo -e "${NC}${YELLOW}---------------- Statistiques ----------------${NC}"
    echo -e "Fichiers .java: $(find . -name "*.java" -not -path "./target/*" | wc -l)"
    echo -e "Fichiers .jsp:  $(find . -name "*.jsp" -not -path "./target/*" | wc -l)"
    echo -e "Fichiers .css:  $(find . -name "*.css" -not -path "./target/*" | wc -l)"
    echo -e "Fichiers .sql:  $(find . -name "*.sql" -not -path "./target/*" | wc -l)"
    echo -e "Fichiers .xml:  $(find . -name "*.xml" -not -path "./target/*" | wc -l)"
    echo -e "Fichiers .properties: $(find . -name "*.properties" -not -path "./target/*" | wc -l)"
    total=$(find . -type f -not -path "./target/*" -not -path "./.git/*" -not -name "*.class" | wc -l)
    echo -e "${GREEN}Total: $total fichiers${NC}"
}

# Fonction pour afficher la structure
show_structure() {
    echo -e "${BLUE}Structure du projet:${NC}"
    echo -e "${CYAN}"
    
    # Utiliser tree si disponible, sinon utiliser find
    if command -v tree &> /dev/null; then
        tree -I 'target|*.class|*.jar' -L 3
    else
        find . -type f -name "*.java" -o -name "*.jsp" -o -name "*.properties" -o -name "pom.xml" \
            | grep -v "./target" | sort | sed 's|^./||' | head -30
        echo -e "${YELLOW}(Affichage limité à 30 fichiers)${NC}"
    fi
    echo -e "${NC}"
}

# Fonction pour créer source.txt
create_source_txt() {
    echo -e "${BLUE}Création de source.txt avec tous les fichiers de code...${NC}"
    
    > source.txt  # Vider le fichier
    
    # Fichiers Java
    find . -name "*.java" -type f -not -path "./target/*" | sort | while read java_file; do
        echo "========================================================================" >> source.txt
        echo "=== Fichier Java: ${java_file:2}" >> source.txt
        echo "========================================================================" >> source.txt
        echo "" >> source.txt
        cat "$java_file" >> source.txt
        echo "" >> source.txt
        echo "" >> source.txt
    done
    
    # Fichiers JSP
    find . -name "*.jsp" -type f -not -path "./target/*" | sort | while read jsp_file; do
        echo "========================================================================" >> source.txt
        echo "=== Fichier JSP: ${jsp_file:2}" >> source.txt
        echo "========================================================================" >> source.txt
        echo "" >> source.txt
        cat "$jsp_file" >> source.txt
        echo "" >> source.txt
        echo "" >> source.txt
    done
    
    # Fichiers properties
    find . -name "*.properties" -type f -not -path "./target/*" | sort | while read prop_file; do
        echo "========================================================================" >> source.txt
        echo "=== Fichier Properties: ${prop_file:2}" >> source.txt
        echo "========================================================================" >> source.txt
        echo "" >> source.txt
        cat "$prop_file" >> source.txt
        echo "" >> source.txt
        echo "" >> source.txt
    done
    
    # Fichiers SQL
    find . -name "*.sql" -type f -not -path "./target/*" | sort | while read sql_file; do
        echo "========================================================================" >> source.txt
        echo "=== Fichier SQL: ${sql_file:2}" >> source.txt
        echo "========================================================================" >> source.txt
        echo "" >> source.txt
        cat "$sql_file" >> source.txt
        echo "" >> source.txt
        echo "" >> source.txt
    done
    
    count=$(find . -type f \( -name "*.java" -o -name "*.jsp" -o -name "*.properties" -o -name "*.sql" \) -not -path "./target/*" | wc -l)
    echo -e "${GREEN}✓ source.txt créé avec $count fichiers${NC}"
    echo -e "  Taille: $(du -h source.txt | cut -f1)"
    echo -e "  Lignes: $(wc -l < source.txt)"
}

# Fonction pour vérifier les dépendances
check_dependencies() {
    echo -e "${BLUE}Vérification des dépendances Maven...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        return 1
    fi
    
    echo -e "${CYAN}Liste des dépendances:${NC}"
    mvn dependency:tree | grep -E "(INFO|\[INFO\]\+-)" | head -50
    
    echo -e "\n${CYAN}Vérification des mises à jour:${NC}"
    mvn versions:display-dependency-updates | grep -A5 "The following dependencies"
}

# Fonction pour redémarrer PostgreSQL
restart_postgres() {
    echo -e "${BLUE}Redémarrage de PostgreSQL...${NC}"
    
    if ! command -v systemctl &> /dev/null; then
        echo -e "${RED}systemctl n'est pas disponible${NC}"
        return 1
    fi
    
    sudo systemctl restart postgresql
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ PostgreSQL redémarré avec succès${NC}"
        sleep 2
        sudo systemctl status postgresql --no-pager | head -10
    else
        echo -e "${RED}✗ Échec du redémarrage${NC}"
    fi
}

# Fonction pour vérifier l'état des services
check_services() {
    echo -e "${BLUE}État des services:${NC}"
    
    echo -e "\n${CYAN}1. PostgreSQL:${NC}"
    if systemctl is-active --quiet postgresql; then
        echo -e "${GREEN}  ✓ En cours d'exécution${NC}"
    else
        echo -e "${RED}  ✗ Arrêté${NC}"
    fi
    
    echo -e "\n${CYAN}2. Java:${NC}"
    if command -v java &> /dev/null; then
        java_version=$(java -version 2>&1 | head -1 | cut -d '"' -f2)
        echo -e "${GREEN}  ✓ Installé (version: $java_version)${NC}"
    else
        echo -e "${RED}  ✗ Non installé${NC}"
    fi
    
    echo -e "\n${CYAN}3. Maven:${NC}"
    if command -v mvn &> /dev/null; then
        mvn_version=$(mvn -v | grep "Apache Maven" | cut -d ' ' -f3)
        echo -e "${GREEN}  ✓ Installé (version: $mvn_version)${NC}"
    else
        echo -e "${RED}  ✗ Non installé${NC}"
    fi
    
    echo -e "\n${CYAN}4. Port 8099 (application):${NC}"
    if netstat -tuln | grep -q ":8099"; then
        echo -e "${RED}  ✗ Port 8099 occupé${NC}"
    else
        echo -e "${GREEN}  ✓ Port 8099 disponible${NC}"
    fi
}

# Fonction pour générer la documentation
generate_documentation() {
    echo -e "${BLUE}Génération de la documentation...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        return 1
    fi
    
    echo -e "${CYAN}1. Documentation Java (Javadoc):${NC}"
    mvn javadoc:javadoc
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Documentation générée dans target/site/apidocs/${NC}"
    fi
    
    echo -e "\n${CYAN}2. Documentation du projet:${NC}"
    if [ -f "README.md" ]; then
        echo -e "${GREEN}✓ README.md trouvé${NC}"
        echo "Contenu:"
        head -20 README.md
    else
        echo -e "${YELLOW}⚠ README.md non trouvé${NC}"
    fi
}

# Fonction pour exécuter les tests
run_tests() {
    echo -e "${BLUE}Exécution des tests...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}Maven n'est pas installé!${NC}"
        return 1
    fi
    
    echo -e "${CYAN}Exécution de: mvn test${NC}"
    mvn test
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Tests exécutés avec succès${NC}"
    else
        echo -e "${RED}✗ Certains tests ont échoué${NC}"
    fi
}

# Fonction pour diagnostiquer PostgreSQL
diagnose_postgres() {
    echo -e "${BLUE}Diagnostic PostgreSQL détaillé...${NC}"
    
    # Vérifier le service
    echo -e "\n${CYAN}1. État du service:${NC}"
    sudo systemctl status postgresql --no-pager
    
    # Vérifier la connexion
    echo -e "\n${CYAN}2. Test de connexion:${NC}"
    PGPASSWORD=postgres psql -h localhost -U postgres -d postgres -c "SELECT version();" 2>/dev/null
    if [ $? -ne 0 ]; then
        echo -e "${RED}✗ Connexion échouée${NC}"
        
        echo -e "\n${CYAN}3. Tentative de réparation:${NC}"
        read -p "Voulez-vous réinitialiser le mot de PostgreSQL? (o/n): " choice
        if [[ $choice == "o" || $choice == "O" ]]; then
            echo -e "${YELLOW}Mot de passe actuel de l'utilisateur Linux:${NC}"
            sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD 'postgres';"
            echo -e "${GREEN}✓ Mot de passe réinitialisé à 'postgres'${NC}"
        fi
    else
        echo -e "${GREEN}✓ Connexion réussie${NC}"
    fi
    
    # Vérifier les bases de données
    echo -e "\n${CYAN}4. Bases de données:${NC}"
    sudo -u postgres psql -c "\l" 2>/dev/null
    
    # Vérifier pg_hba.conf
    echo -e "\n${CYAN}5. Configuration d'authentification:${NC}"
    pg_hba_path=$(sudo find / -name "pg_hba.conf" 2>/dev/null | head -1)
    if [ -n "$pg_hba_path" ]; then
        echo -e "Fichier trouvé: $pg_hba_path"
        sudo grep -E "(local|host.*127.0.0.1)" "$pg_hba_path" | head -5
    else
        echo -e "${YELLOW}Fichier pg_hba.conf non trouvé${NC}"
    fi
}

# Menu principal
main() {
    while true; do
        print_header
        print_menu
        
        read -p "Votre choix [0-12]: " choice
        
        case $choice in
            1)
                run_application
                ;;
            2)
                compile_project
                ;;
            3)
                clean_project
                ;;
            4)
                test_postgres
                ;;
            5)
                list_all_files
                ;;
            6)
                show_structure
                ;;
            7)
                create_source_txt
                ;;
            8)
                check_dependencies
                ;;
            9)
                restart_postgres
                ;;
            10)
                check_services
                ;;
            11)
                generate_documentation
                ;;
            12)
                run_tests
                ;;
            0)
                echo -e "${CYAN}Au revoir!${NC}"
                exit 0
                ;;
            *)
                echo -e "${RED}Choix invalide!${NC}"
                ;;
        esac
        
        echo ""
        echo -e "${YELLOW}----------------------------------------${NC}"
        read -p "Appuyez sur Entrée pour continuer..."
        clear
    done
}

# Vérifier les dépendances au démarrage
check_prerequisites() {
    echo -e "${CYAN}Vérification des prérequis...${NC}"
    
    errors=0
    
    # Vérifier Java
    if ! command -v java &> /dev/null; then
        echo -e "${RED}✗ Java n'est pas installé${NC}"
        errors=$((errors + 1))
    else
        java_version=$(java -version 2>&1 | head -1 | cut -d '"' -f2)
        echo -e "${GREEN}✓ Java installé (version: $java_version)${NC}"
    fi
    
    # Vérifier Maven
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}✗ Maven n'est pas installé${NC}"
        errors=$((errors + 1))
    else
        mvn_version=$(mvn -v | grep "Apache Maven" | cut -d ' ' -f3)
        echo -e "${GREEN}✓ Maven installé (version: $mvn_version)${NC}"
    fi
    
    # Vérifier PostgreSQL
    if ! command -v psql &> /dev/null; then
        echo -e "${YELLOW}⚠ PostgreSQL n'est pas installé (optionnel pour H2)${NC}"
    else
        echo -e "${GREEN}✓ PostgreSQL installé${NC}"
    fi
    
    if [ $errors -gt 0 ]; then
        echo -e "\n${RED}Des prérequis sont manquants.${NC}"
        echo "Installez les avec:"
        echo "  sudo apt install default-jdk maven"
        read -p "Voulez-vous continuer quand même? (o/n): " choice
        if [[ $choice != "o" && $choice != "O" ]]; then
            exit 1
        fi
    fi
    
    sleep 2
}

# Démarrer le programme
clear
check_prerequisites
main