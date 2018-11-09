DROP TABLE public."TrnPartDefuncionDemo";
CREATE TABLE public."TrnPartDefuncionDemo"
(
  "IdCiudadano" integer NOT NULL,
  "IdNroOficialia" integer,
  "FechaInscripcion" date NOT NULL,
  "NombresFallecido" character varying(100),
  "PaisInscripcion" integer NOT NULL,
  "IdLocInscripcion" integer NOT NULL,
  CONSTRAINT "TrnPartDefuncionDemo_pkey" PRIMARY KEY ("IdCiudadano")
)