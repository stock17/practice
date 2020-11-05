CREATE TABLE IF NOT EXISTS Document_type (
    code    INTEGER         NOT NULL    COMMENT 'Код документа' PRIMARY KEY,
    version INTEGER         NOT NULL    COMMENT 'Служебное поле hibernate',
    name    VARCHAR(127)    NOT NULL    COMMENT 'Наименование вида документа'
);


CREATE TABLE IF NOT EXISTS Country (
    code    INTEGER     NOT NULL    COMMENT 'Код страны' PRIMARY KEY,
    version INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    name    VARCHAR(50) NOT NULL    COMMENT 'Наименование страны'
);


CREATE TABLE IF NOT EXISTS Document (
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    doc_number      BIGINT      NOT NULL    COMMENT 'Номер документа',
    doc_date        DATE        NOT NULL    COMMENT 'Дата выдачи документа',
    doc_code        INTEGER     NOT NULL    COMMENT 'Код документа'
);

CREATE INDEX IX_Document_doc_code ON Document (doc_code);
ALTER TABLE Document ADD FOREIGN KEY (doc_code) REFERENCES Document_type(code);


CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER      NOT NULL    COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50)  NOT NULL    COMMENT 'Наименование',
    full_name   VARCHAR(255) NOT NULL    COMMENT 'Полное наименование',
    inn         VARCHAR(10)  NOT NULL    COMMENT 'Идентификационный налоговый номер',
    kpp         VARCHAR(9)   NOT NULL    COMMENT 'Код причины постановки',
    address     VARCHAR(255) NOT NULL    COMMENT 'Почтовый адрес',
    phone       VARCHAR(20)  NOT NULL    COMMENT 'Телефон',
    is_active   BOOLEAN      NOT NULL    COMMENT 'Статус (действующий)'
);
COMMENT ON TABLE Organization IS 'Организация';


CREATE TABLE IF NOT EXISTS Office (
    id              INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER      NOT NULL    COMMENT 'Служебное поле hibernate',
    name            VARCHAR(50)  NOT NULL    COMMENT 'Наименование',    
    address         VARCHAR(255) NOT NULL    COMMENT 'Почтовый адрес',
    phone           VARCHAR(20)  NOT NULL    COMMENT 'Телефон',
    is_active       BOOLEAN      NOT NULL    COMMENT 'Статус (действующий)', 
    organization_id INTEGER      NOT NULL    COMMENT 'Id организации'    
);
COMMENT ON TABLE Office IS 'Офис';

CREATE INDEX IX_Office_organization_id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);


CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER         NOT NULL    COMMENT 'Служебное поле hibernate',
    first_name          VARCHAR(50)     NOT NULL    COMMENT 'Имя',
    middle_name         VARCHAR(50)                 COMMENT 'Отчество',
    second_name         VARCHAR(50)     NOT NULL    COMMENT 'Фамилия',
    position            VARCHAR(50)     NOT NULL    COMMENT 'Должность',
    phone               VARCHAR(20)     NOT NULL    COMMENT 'Телефон',
    address             VARCHAR(255)    NOT NULL    COMMENT 'Почтовый адрес',
    is_identified       BOOLEAN         NOT NULL    COMMENT 'Статус (идентифицирован)',
    office_id           INTEGER         NOT NULL    COMMENT 'Id oфиса',
    document_id         INTEGER         NOT NULL    COMMENT 'Документ',
    citizenship         INTEGER         NOT NULL    COMMENT 'Гражданство'
);
COMMENT ON TABLE User IS 'Работник';

CREATE INDEX IX_User_office_id   ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id)   REFERENCES Office(id);

CREATE UNIQUE INDEX UX_User_document_id ON User (document_id);
ALTER TABLE User ADD FOREIGN KEY (document_id) REFERENCES Document(id);

CREATE INDEX IX_User_citizenship ON User (citizenship);
ALTER TABLE User ADD FOREIGN KEY (citizenship) REFERENCES Country(code);