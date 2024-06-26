CREATE SEQUENCE SQ_PROFESORES INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999999999999999 CACHE 20;

CREATE TABLE "PROFESORES" 
   ("ID_PROFESOR" NUMBER(12,0) DEFAULT SQ_PROFESORES.NEXTVAL NOT NULL ENABLE, 
	"ID_USUARIO" NUMBER(12,0) NOT NULL ENABLE,
	"DESCRIPCION" VARCHAR2(1000) NOT NULL ENABLE, 
	"ID_MATERIA" NUMBER(12,0) NOT NULL ENABLE, 
	"PRECIO" NUMBER(2) NOT NULL ENABLE, 
	CONSTRAINT "PRO_PK" PRIMARY KEY ("ID_PROFESOR"),
	CONSTRAINT "PRO_USUARIO_FK" FOREIGN KEY ("ID_USUARIO")
	  REFERENCES "USUARIOS" ("ID_USUARIO") ENABLE,
	CONSTRAINT "PRO_MATERIA_FK" FOREIGN KEY ("ID_MATERIA")
	  REFERENCES "MATERIAS" ("ID_MATERIA") ENABLE);

CREATE INDEX PROFESORES_ID_USUARIO ON PROFESORES (ID_USUARIO);
CREATE INDEX PROFESORES_DESCRIPCION ON PROFESORES (DESCRIPCION);
CREATE INDEX PROFESORES_ID_MATERIA ON PROFESORES (ID_MATERIA);
CREATE INDEX PROFESORES_PRECIO ON PROFESORES (PRECIO);

COMMENT ON COLUMN "PROFESORES"."ID_PROFESOR" IS 'IDENTIFICADOR DEL PROFESOR';
COMMENT ON COLUMN "PROFESORES"."ID_USUARIO" IS 'IDENTIFICADOR DEL USUARIO';
COMMENT ON COLUMN "PROFESORES"."DESCRIPCION" IS 'DESCRIPCIÓN/SOBRE MI DEL PROFESOR';
COMMENT ON COLUMN "PROFESORES"."ID_MATERIA" IS 'IDENTIFICADOR DE LA MATERIA';
COMMENT ON COLUMN "PROFESORES"."PRECIO" IS 'PRECIO DE LA CLASE DEL PROFESOR';

INSERT INTO PROFESORES (ID_USUARIO, DESCRIPCION, ID_MATERIA, PRECIO) VALUES (2, 'Descripción de prueba', 1, 20); 