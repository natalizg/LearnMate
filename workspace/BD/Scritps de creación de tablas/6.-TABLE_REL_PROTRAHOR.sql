CREATE SEQUENCE SQ_REL_PROTRAHOR INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999999999999999 CACHE 20;

CREATE TABLE "REL_PROTRAHOR" 
   ("ID_PROTRAHOR" NUMBER(12,0) DEFAULT SQ_REL_PROTRAHOR.NEXTVAL NOT NULL ENABLE,  
	"ID_PROFESOR" NUMBER(12,0) NOT NULL ENABLE,  
	"ID_TRAMO_HORARIO" NUMBER(12,0) NOT NULL ENABLE, 
	CONSTRAINT "PROTRAHOR_PK" PRIMARY KEY ("ID_PROTRAHOR"),
	CONSTRAINT "PROTRAHOR_PROFESOR_FK" FOREIGN KEY ("ID_PROFESOR")
	  REFERENCES "PROFESORES" ("ID_PROFESOR") ENABLE,
	CONSTRAINT "PROTRAHOR_TRAMO_HORARIO_FK" FOREIGN KEY ("ID_TRAMO_HORARIO")
	  REFERENCES "TRAMOS_HORARIOS" ("ID_TRAMO_HORARIO") ENABLE);

CREATE INDEX REL_PROTRAHOR_ID_PROFESOR ON REL_PROTRAHOR (ID_PROFESOR);
CREATE INDEX REL_PROTRAHOR_ID_TRAMO_HORARIO ON REL_PROTRAHOR (ID_TRAMO_HORARIO);

COMMENT ON COLUMN "REL_PROTRAHOR"."ID_PROTRAHOR" IS 'IDENTIFICADOR DE LA RELACIÓN DEL PROFESOR CON EL TRAMO HORARIO';
COMMENT ON COLUMN "REL_PROTRAHOR"."ID_PROFESOR" IS 'IDENTIFICADOR DEL PROFESOR';
COMMENT ON COLUMN "REL_PROTRAHOR"."ID_TRAMO_HORARIO" IS 'IDENTIFICADOR DEL TRAMO HORARIO';

INSERT INTO REL_PROTRAHOR (ID_PROFESOR, ID_TRAMO_HORARIO) VALUES (1, 1); 
INSERT INTO REL_PROTRAHOR (ID_PROFESOR, ID_TRAMO_HORARIO) VALUES (1, 2); 
INSERT INTO REL_PROTRAHOR (ID_PROFESOR, ID_TRAMO_HORARIO) VALUES (1, 3); 