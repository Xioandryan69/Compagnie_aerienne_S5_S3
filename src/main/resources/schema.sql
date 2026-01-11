-- =========================
-- TABLE AEROPORT
-- =========================
CREATE TABLE IF NOT EXISTS aeroport (
    code_iata VARCHAR(3) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    ville VARCHAR(100) NOT NULL,
    pays VARCHAR(100) NOT NULL
);

-- =========================
-- TABLE AVION
-- =========================
CREATE TABLE IF NOT EXISTS avion (
    id BIGSERIAL PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    capacite INT NOT NULL,
    etat VARCHAR(30) NOT NULL
);

-- =========================
-- TABLE VOL
-- =========================
CREATE TABLE IF NOT EXISTS vol (
    id BIGSERIAL PRIMARY KEY,
    numero_vol VARCHAR(20) NOT NULL,
    date_vol DATE NOT NULL,
    heure_depart TIME NOT NULL,
    heure_arrivee TIME NOT NULL,
    prix DECIMAL(10,2) NOT NULL,

    depart_iata VARCHAR(3) NOT NULL,
    arrivee_iata VARCHAR(3) NOT NULL,
    avion_id BIGINT NOT NULL,

    CONSTRAINT fk_vol_depart
        FOREIGN KEY (depart_iata)
        REFERENCES aeroport(code_iata),

    CONSTRAINT fk_vol_arrivee
        FOREIGN KEY (arrivee_iata)
        REFERENCES aeroport(code_iata),

    CONSTRAINT fk_vol_avion
        FOREIGN KEY (avion_id)
        REFERENCES avion(id)
);

-- =========================
-- TABLE PASSAGER
-- =========================
CREATE TABLE IF NOT EXISTS passager (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_document VARCHAR(50) NOT NULL,
    nationalite VARCHAR(50),
    email VARCHAR(100),
    telephone VARCHAR(30),
    sexe VARCHAR(10)
);

-- =========================
-- TABLE RESERVATION
-- =========================
CREATE TABLE IF NOT EXISTS reservation (
    id BIGSERIAL PRIMARY KEY,
    date_reservation TIMESTAMP NOT NULL,
    statut VARCHAR(30) NOT NULL,

    vol_id BIGINT NOT NULL,
    passager_id BIGINT NOT NULL,

    CONSTRAINT fk_reservation_vol
        FOREIGN KEY (vol_id)
        REFERENCES vol(id),

    CONSTRAINT fk_reservation_passager
        FOREIGN KEY (passager_id)
        REFERENCES passager(id)
);

CREATE TABLE IF NOT EXISTS option_supplementaire (
    id BIGSERIAL PRIMARY KEY,
    type_option VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    prix DECIMAL(10,2) NOT NULL,
    reservation_id BIGINT NOT NULL,
    CONSTRAINT fk_option_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(id)
);
