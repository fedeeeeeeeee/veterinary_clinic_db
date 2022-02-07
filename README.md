Per testare l' applicazione occorre importare il progetto in Eclipse e andare nella classe DBConnection.java all'interno del package database per inserire root, password e porta (nel DbUri).

In MySQL occorre creare il database clinicaveterinaria (create database clinicaveterinaria) e successivamente importare le tabelle inclusive di dati tramite le istruzioni in linguaggio SQL riportate più avanti.

A questo punto, dopo aver avviato l'applicazione si può effettuare il login con uno degli utenti presenti nel database oppure registrarsi creando un nuovo utente.

Sono qui riportate le credenziali di due utenti che possono accedere all'applicazione e che ricoprono ruoli diversi:
SEGRETARIA
username: 'sg@sg.sg'
password: 's'

VETERINARIO
username: 'sa@sa.sa'
password: 's'

-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: clinicaveterinaria
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animale`
--

DROP TABLE IF EXISTS `animale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animale` (
  `codAnimale` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `genere` char(1) NOT NULL,
  `dataDiNascita` date NOT NULL,
  `peso` float NOT NULL DEFAULT '0',
  `altezza` float NOT NULL DEFAULT '0',
  `razza` varchar(20) NOT NULL,
  `specie` varchar(20) NOT NULL,
  `CF` char(16) NOT NULL,
  PRIMARY KEY (`codAnimale`),
  UNIQUE KEY `ID_ANIMALE_IND` (`codAnimale`),
  KEY `EQU_ANIMA_PROPR_IND` (`CF`),
  CONSTRAINT `EQU_ANIMA_PROPR_FK` FOREIGN KEY (`CF`) REFERENCES `proprietario` (`CF`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animale`
--

LOCK TABLES `animale` WRITE;
/*!40000 ALTER TABLE `animale` DISABLE KEYS */;
INSERT INTO `animale` VALUES (1,'Levi','M','2015-12-17',1.5,10,'Carlino','Cane','CRNLSS80M59H282P'),(2,'Giulietta','F','2019-07-20',5,20,'Soriano','Gatto','DMZPDN80C45D810M'),(3,'Babe','M','2014-04-01',30.2,25.5,'Maialino nano','Maialino','MNGMRA80M19D548I'),(4,'Sabbia','F','2020-04-16',30,40,'Labrador retriever','Cane','DMZPDN80C45D810M'),(5,'Nebbia','M','2013-09-21',40,55,'Golden retriever','Cane','DMZPDN80C45D810M'),(6,'Zoro','M','2019-12-01',56,62,'Terranova','Cane','CRNLSS80M59H282P'),(7,'Bambi','M','2020-02-01',28.5,30,'Coniglio ariete nano','Coniglio','PLNNCL93B06D458I');
/*!40000 ALTER TABLE `animale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `effettua`
--

DROP TABLE IF EXISTS `effettua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `effettua` (
  `codAnimale` int NOT NULL,
  `codTipoPrestazione` int NOT NULL,
  `data` date NOT NULL,
  `CF` char(16) NOT NULL,
  PRIMARY KEY (`CF`,`codAnimale`,`codTipoPrestazione`,`data`),
  UNIQUE KEY `ID_effettua_IND` (`CF`,`codAnimale`,`codTipoPrestazione`,`data`),
  KEY `REF_effet_PREST_IND` (`codAnimale`,`codTipoPrestazione`,`data`),
  CONSTRAINT `REF_effet_PERSO` FOREIGN KEY (`CF`) REFERENCES `personale` (`CF`),
  CONSTRAINT `REF_effet_PREST_FK` FOREIGN KEY (`codAnimale`, `codTipoPrestazione`, `data`) REFERENCES `prestazione_su_animale` (`codAnimale`, `codTipoPrestazione`, `data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `effettua`
--

LOCK TABLES `effettua` WRITE;
/*!40000 ALTER TABLE `effettua` DISABLE KEYS */;
INSERT INTO `effettua` VALUES (1,5,'2015-02-27','CHRRRA80D45A944S'),(1,5,'2015-02-27','MRCLEA65T46F288U'),(1,5,'2016-02-27','MRCLEA65T46F288U'),(1,5,'2016-02-27','RGNCST90D48A944W'),(1,5,'2017-02-27','MRCLEA65T46F288U'),(1,5,'2017-02-27','RGNCST90D48A944W'),(1,5,'2018-02-27','MRCLEA65T46F288U'),(1,5,'2018-02-27','RGNCST90D48A944W'),(1,5,'2019-02-27','MRCLEA65T46F288U'),(1,5,'2019-02-27','RGNCST90D48A944W'),(1,5,'2020-02-27','MRCLEA65T46F288U'),(1,5,'2020-02-27','RGNCST90D48A944W'),(1,5,'2021-02-27','MRCLEA65T46F288U'),(1,5,'2021-02-27','RGNCST90D48A944W'),(1,5,'2022-02-09','PNNNCL79R03A944K'),(1,5,'2022-02-09','uiuiuiuiuiuiuiui'),(1,11,'2022-02-05','MRCLEA65T46F288U'),(1,11,'2022-02-05','PLLGNN90H04A944I'),(3,10,'2022-02-08','DPLVLN61R46F597V'),(3,10,'2022-02-08','RGNCST90D48A944W'),(5,5,'2022-02-08','RGNCST90D48A944W'),(5,5,'2022-02-08','uiuiuiuiuiuiuiui'),(5,10,'2022-02-09','MRCLEA65T46F288U'),(5,10,'2022-02-09','RGNCST90D48A944W'),(5,11,'2022-02-07','CHRRRA80D45A944S'),(5,11,'2022-02-07','uiuiuiuiuiuiuiui'),(6,10,'2022-02-05','PLLGNN90H04A944I'),(6,10,'2022-02-05','uiuiuiuiuiuiuiui'),(6,10,'2022-02-07','CHRRRA80D45A944S'),(6,10,'2022-02-07','MRCLEA65T46F288U'),(7,11,'2022-02-05','CHRRRA80D45A944S'),(7,11,'2022-02-05','uiuiuiuiuiuiuiui'),(7,11,'2022-02-07','CHRRRA80D45A944S'),(7,11,'2022-02-07','uiuiuiuiuiuiuiui');
/*!40000 ALTER TABLE `effettua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmaco`
--

DROP TABLE IF EXISTS `farmaco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farmaco` (
  `codFarmaco` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `descrizione` varchar(400) NOT NULL,
  `disponibilita` int DEFAULT '0',
  `tipoFarmaco` varchar(30) NOT NULL,
  `copertura` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`codFarmaco`),
  UNIQUE KEY `ID_FARMACO_IND` (`codFarmaco`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaco`
--

LOCK TABLES `farmaco` WRITE;
/*!40000 ALTER TABLE `farmaco` DISABLE KEYS */;
INSERT INTO `farmaco` VALUES (1,'Allerderm Spot-On','Assicura la funzione di barriera cutanea: azione calmante e idratante. Migliora la vitalità della cute con effetto antiadesivo contro i microrganismi e stimolazione delle naturali difese microbiche della cute.',27,'Farmaco',NULL),(2,'Albipen L.A.','Medicinale veterinario a base del principio attivo Ampicillina, appartenente alla categoria degli Antibatterici penicillinici. Viene somministrato per: infezioni del tratto gastrointestinale, del tratto urinario, del tratto respiratorio ed infezioni uterine ed ombelicali, mastiti, (poli-)artriti, setticemia, infezioni secondarie a malattie virali.',20,'Farmaco',NULL),(3,'Nobivac Ceppi','Nobivac Ceppi è un medicinale veterinario a base del principio attivo Vaccino Cimurro Per Cane Vl, vaccino Epatite Contag, vaccino Parainfluenza, Vaccino Parvovirosi Canina Vivo. Stimolazione dell\'immunità attiva nei confronti dei virus del Cimurro, dell\'Epatite Infettiva, della Parvovirosi, della Parainfluenza e delle infezioni respiratorie del cane sostenute da Adenovirus tipo 2',10,'Vaccino','3 anni'),(4,'Dexdomitor','Dexdomitor è usato per sedare i cani e i gatti in esami poco o moderatamente dolorosi in cui l’animale deve essere\nimmobilizzato o sedato e reso meno sensibile al dolore (analgesia). È usato in procedure non invasive che non comportano l’incisione della pelle o la penetrazione in una cavità\ndell’organismo.',0,'Farmaco',NULL),(5,'Ketamina Molteni','Farmaco a base del principio attivo Ketamina Cloridrato, appartenente alla categoria degli Anestetici generali. Come unico anestetico per manovre chirurgiche e diagnostiche. Nonostante sia più indicato per interventi brevi, KETAMINA MOLTENI può essere usato, con dosi addizionali, per interventi di maggiore durata.',0,'Farmaco',NULL),(6,'Enrofloxacina','L’enrofloxacina è un antibiotico battericida a largo spettro, sia tra i gram (+) che tra i gram (-).Tale antibiotico viene usato nel trattamento di osteomeliti, infezioni al seno, otiti, infezioni gravi a tessuti molli, peritoniti e leuriti o polmoniti.',0,'Farmaco',NULL),(7,'Tanax','Per l\'eutanasia di cani, gatti e animali di piccola taglia non destinati alla produzione di alimenti per il consumo umano.',25,'Farmaco',NULL),(8,'Ketoprofene','Antinfiammatorio non steroideo e inibitore della ciclo-ossigenasi. Viene utilizzato come analgesico, antipiretico ed antinfiammatorio, ed comunemente prescritto per dolori muscolo-scheletrici ai tessuti molli, artrosi o altre ossa e problemi articolari, ma anche per ridurre o controllare la febbre dovuta ad infezioni virali o batteriche',0,'Farmaco',NULL),(9,'INAujeszky A-Suivax GI','Medicinale veterinario a base del principio attivo Vaccino Malattia Di Aujeszky Per Il Suino Inattivato, appartenente alla categoria degli Vaccini e nello specifico Vaccini virali inattivati',0,'Vaccino','10 anni');
/*!40000 ALTER TABLE `farmaco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmaco_in_prestazione`
--

DROP TABLE IF EXISTS `farmaco_in_prestazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farmaco_in_prestazione` (
  `codAnimale` int NOT NULL,
  `codTipoPrestazione` int NOT NULL,
  `data` date NOT NULL,
  `codFarmaco` int NOT NULL,
  `dataInizioSomministrazione` date NOT NULL,
  `dataFineSomministrazione` date NOT NULL,
  `indicazioni` varchar(50) NOT NULL,
  PRIMARY KEY (`codAnimale`,`codTipoPrestazione`,`data`,`codFarmaco`),
  UNIQUE KEY `ID_FARMACO_IN_PRESTAZIONE_IND` (`codAnimale`,`codTipoPrestazione`,`data`,`codFarmaco`),
  KEY `REF_FARMA_FARMA_IND` (`codFarmaco`),
  CONSTRAINT `REF_FARMA_FARMA_FK` FOREIGN KEY (`codFarmaco`) REFERENCES `farmaco` (`codFarmaco`),
  CONSTRAINT `REF_FARMA_PREST` FOREIGN KEY (`codAnimale`, `codTipoPrestazione`, `data`) REFERENCES `prestazione_su_animale` (`codAnimale`, `codTipoPrestazione`, `data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaco_in_prestazione`
--

LOCK TABLES `farmaco_in_prestazione` WRITE;
/*!40000 ALTER TABLE `farmaco_in_prestazione` DISABLE KEYS */;
INSERT INTO `farmaco_in_prestazione` VALUES (6,10,'2022-02-05',8,'2022-02-05','2022-02-11','1 al giorno giorni'),(7,11,'2022-02-05',1,'2022-02-05','2030-02-01','1 ogni 6 mesi'),(7,11,'2022-02-07',8,'2022-02-07','2022-02-11','1 al giorno');
/*!40000 ALTER TABLE `farmaco_in_prestazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lotto_acquistato`
--

DROP TABLE IF EXISTS `lotto_acquistato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lotto_acquistato` (
  `codLotto` int NOT NULL AUTO_INCREMENT,
  `numeroConfezioni` int NOT NULL,
  `quantitaPerConfezione` int NOT NULL,
  `dataDiScadenza` date NOT NULL,
  `codFarmaco` int NOT NULL,
  PRIMARY KEY (`codLotto`),
  UNIQUE KEY `ID_LOTTO_ACQUISTATO_IND` (`codLotto`),
  KEY `REF_LOTTO_FARMA_IND` (`codFarmaco`),
  CONSTRAINT `REF_LOTTO_FARMA_FK` FOREIGN KEY (`codFarmaco`) REFERENCES `farmaco` (`codFarmaco`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lotto_acquistato`
--

LOCK TABLES `lotto_acquistato` WRITE;
/*!40000 ALTER TABLE `lotto_acquistato` DISABLE KEYS */;
INSERT INTO `lotto_acquistato` VALUES (11,2,5,'2023-02-01',3),(12,4,5,'2024-04-17',1),(13,2,4,'2021-02-07',1),(14,2,10,'2021-02-01',2),(15,5,5,'2022-02-10',7);
/*!40000 ALTER TABLE `lotto_acquistato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patologia`
--

DROP TABLE IF EXISTS `patologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patologia` (
  `codPatologia` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(150) NOT NULL,
  `tipoPatologia` varchar(30) NOT NULL,
  PRIMARY KEY (`codPatologia`),
  UNIQUE KEY `ID_PATOLOGIA_IND` (`codPatologia`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patologia`
--

LOCK TABLES `patologia` WRITE;
/*!40000 ALTER TABLE `patologia` DISABLE KEYS */;
INSERT INTO `patologia` VALUES (1,'incinta',' ','patologia'),(2,'morte celebrale',' ','patologia'),(3,'tumore mammelle',' ','patologia'),(4,'tumore arto sinistro',' ','patologia'),(5,'dermatite','chiazze sull\'animale','patologia'),(6,'tumore arto sinistro',' ','patologia'),(7,'allergia alimenti (RAC- RAAMM)',' ','allergia'),(8,'Febbre','','Patologia');
/*!40000 ALTER TABLE `patologia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patologia_in_prestazione`
--

DROP TABLE IF EXISTS `patologia_in_prestazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patologia_in_prestazione` (
  `codAnimale` int NOT NULL,
  `codTipoPrestazione` int NOT NULL,
  `data` date NOT NULL,
  `codPatologia` int NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date DEFAULT NULL,
  PRIMARY KEY (`codAnimale`,`codTipoPrestazione`,`data`,`codPatologia`),
  UNIQUE KEY `ID_PATOLOGIA_IN_PRESTAZIONE_IND` (`codAnimale`,`codTipoPrestazione`,`data`,`codPatologia`),
  KEY `REF_PATOL_PATOL_IND` (`codPatologia`),
  CONSTRAINT `REF_PATOL_PATOL_FK` FOREIGN KEY (`codPatologia`) REFERENCES `patologia` (`codPatologia`),
  CONSTRAINT `REF_PATOL_PREST` FOREIGN KEY (`codAnimale`, `codTipoPrestazione`, `data`) REFERENCES `prestazione_su_animale` (`codAnimale`, `codTipoPrestazione`, `data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patologia_in_prestazione`
--

LOCK TABLES `patologia_in_prestazione` WRITE;
/*!40000 ALTER TABLE `patologia_in_prestazione` DISABLE KEYS */;
INSERT INTO `patologia_in_prestazione` VALUES (5,11,'2022-02-07',5,'2022-02-07',NULL),(6,10,'2022-02-05',5,'2022-02-05',NULL),(7,11,'2022-02-07',8,'2022-02-07',NULL);
/*!40000 ALTER TABLE `patologia_in_prestazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personale`
--

DROP TABLE IF EXISTS `personale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personale` (
  `CF` char(16) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `ind_citta` varchar(50) NOT NULL,
  `ind_via` varchar(30) NOT NULL,
  `ind_numero` int NOT NULL,
  `ind_cap` int NOT NULL,
  `telefono` bigint NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `anniDiServizio` int NOT NULL,
  `password` char(128) NOT NULL,
  `sale` char(4) NOT NULL,
  `ruolo` char(20) NOT NULL,
  `dataDiNascita` date DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `ID_PERSONALE_IND` (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personale`
--

LOCK TABLES `personale` WRITE;
/*!40000 ALTER TABLE `personale` DISABLE KEYS */;
INSERT INTO `personale` VALUES ('BTLNSI86R45F706N','Ines','Abatello','Monzuno','Via Armando Diaz',68,40036,3798347603,'marianossi@libero.it',11,'b5860ec60ee7935367734448d6a296b90e792c71cbd2487758e86ddd786b3eb65d914da07b73fa735cfb23f83457348bd8908e2ca61177fad2673e433af4a786','BoD1','Segretaria','1992-12-12'),('CHRRRA80D45A944S','Aurora','Chiaraluce','Bologna','Via Petrarca',61,40121,3336945120,'aurorachiaraluce@gmail.com',10,'443e1fb7202fdb461de3a8b79e0a9f1a06c66a64b381890e1f3e245eb3f4253eeee60e81614fdb958c38323d7eec2aff65cd7637f014324ba81d21830f5dcffd','6Ygw','Infermiere','1980-12-12'),('DPLVLN61R46F597V','Evelina','Deplano','Molinella','Via Visconti',7,40050,3517476032,'evelidep@gmail.com',7,'8d608ecd6c81b271904cf31339f86e890f21ea163048c010aa18102a82b19dca8b59564fe387cfeb98cb753499e942f09e61b0fbc8b676bc8ab5408a1e4c40f7','1feF','Veterinario','1987-12-12'),('MRCLEA65T46F288U','Lea','Mirceta','Galliera ','Via Roberto Bracco',45,40015,3890825471,'mrclea36@gmail.com',20,'c645edad1240730dd7bd3513e41b827b60436ca185101c81739c6aac175b17e8949d5ab0e195abce0b20c68c42467e9c22635ac7cd368b34de6f16f02a346800','g8sY','Veterinario','1975-12-12'),('PLLGNN90H04A944I','Giovanni','Pollastro','Bologna','Via da vinci',103,40121,34691679,'giovannipollastro@gmail.com',5,'f998936ec11a8073ca5ac1d711edcaae2c105fad71175ec0231b22c35636564cfcd0140d054108046cfc2e380414acfc832ba7ace55cec8f6ced9bce6dd3e0a2','l0RI','Infermiere','1970-12-12'),('PNNNCL79R03A944K','Nicola','Pennini','Budrio','Via Piave',98,40054,3389674158,'nicolapennini@gmail.com',8,'0f72b9b75c8870318a489c176f2fb924eaf05c432a66800c7529fda3936bb750bc490daced280e4e053ece11edd9ebcaafecbf86a889ca989de5a43d4607eeb4','Aiv1','Infermiere','1980-12-12'),('PRZPLA80A01A223S','Paolo','Perazzini','Alseno','Via giotto',3,29010,3469272820,'paoloperazzini@gmail.com',2,'18dd1bb0f87dad682f2697985f359c7bae6164537f9cb53e46109a63888f262fa05a2e8054d46ff70cde8d6d62163fc7e7ffca7b7659d7d41528d7da4377b911','8ghI','Veterinario','1982-12-12'),('RGNCST90D48A944W','Cristina','Ragnetti','Bologna','Via giotto',6,40121,34698962,'cristinaragnetti@gmail.com',4,'fbfbf296a9a689d71a466d5184d75757a90fba3667d9f7830a1db3d50e7d867e22516b4f3d570e501d330f5cea6503b151fe2be0a76c594176250739b437b899','8E8h','Infermiere','1999-12-12'),('srsrsrsrsrsrsrsr','Paola','Pellegrino','Senigallia','Pio IX',29,60019,2344354657,'sg@sg.sg',3,'ef42842b2dd3c004c7ed214f6442ef44c2ed22fa4ad1e44b89e4c5adb035e57363aea385085b9c0ac349884242ca4e6524fab23d220fb37d2a408331ef05e7e7','oM8L','Segretaria','1992-06-04'),('uiuiuiuiuiuiuiui','Sara','Ragnetti','Cesena','Giotto',8,47888,1245368920,'sa@sa.sa',8,'5eabcb6cd55a8db08dbdbd62f4810fef7001d41abc728a82c89c089506117c7d262cb90b13885be402df8487d0d790ee81e84e4470f74a478afb1487d8c900b3','42GX','Veterinario','1988-08-08');
/*!40000 ALTER TABLE `personale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posto`
--

DROP TABLE IF EXISTS `posto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posto` (
  `numero` int NOT NULL,
  `descrizione` varchar(30) NOT NULL,
  PRIMARY KEY (`numero`),
  UNIQUE KEY `ID_POSTO_IND` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posto`
--

LOCK TABLES `posto` WRITE;
/*!40000 ALTER TABLE `posto` DISABLE KEYS */;
INSERT INTO `posto` VALUES (1,'animali di taglia piccola'),(2,'animali di taglia piccola'),(3,'animali di taglia piccola'),(4,'animali di taglia media'),(5,'animali di taglia media'),(6,'animali di taglia media'),(7,'animali di taglia grande'),(8,'animali di taglia grande'),(9,'animali di taglia grande'),(10,'animali di taglia grande');
/*!40000 ALTER TABLE `posto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestazione`
--

DROP TABLE IF EXISTS `prestazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestazione` (
  `codPrestazione` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `durataMedia` time DEFAULT NULL,
  `prezzo` float DEFAULT '0',
  `descrizione` varchar(150) NOT NULL,
  PRIMARY KEY (`codPrestazione`),
  UNIQUE KEY `ID_PRESTAZIONE_IND` (`codPrestazione`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestazione`
--

LOCK TABLES `prestazione` WRITE;
/*!40000 ALTER TABLE `prestazione` DISABLE KEYS */;
INSERT INTO `prestazione` VALUES (1,'Operazione','Parto Naturale','00:30:00',30,''),(2,'Operazione','Parto Cesareo','01:00:00',60,''),(4,'Visita','Controllo post-operatorio','00:10:00',10,''),(5,'Visita','Controllo parametri','00:10:00',5,''),(6,'Iniezione','Eutanasia','00:20:00',120,''),(7,'Intervento','Esportazione tumore mammelle','00:20:00',230,''),(8,'Intervento','Esportazione tumore agli arti','00:20:00',200,''),(9,'Intervento','Esportazione tumore all\'orecchio ','00:20:00',230,''),(10,'Iniezione','Vaccinazione obbligatoria','00:10:00',20,''),(11,'Visita','Visita su richiesta','00:15:00',10,'Visita prenotata in seguito al verificarsi di sintomi su un animale.'),(12,'Visita','Rilascio ricovero','00:10:00',0,'visita per rilascio ricovero'),(13,'Intervento','Riallineamento osseo','01:00:00',20,'dkjsndo'),(16,'Visita','Visita oculistica','00:30:00',120,'Visita per controllo di patologie agli occhi come cataratta,\nCheratite Superficiale Cronica e atrofia progressiva della retina.\n\n');
/*!40000 ALTER TABLE `prestazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestazione_su_animale`
--

DROP TABLE IF EXISTS `prestazione_su_animale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestazione_su_animale` (
  `codAnimale` int NOT NULL,
  `codTipoPrestazione` int NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `rilevazioneAltezza` float DEFAULT NULL,
  `rilevazionePeso` float DEFAULT NULL,
  PRIMARY KEY (`codAnimale`,`codTipoPrestazione`,`data`),
  UNIQUE KEY `ID_PRESTAZIONE_SU_ANIMALE_IND` (`codAnimale`,`codTipoPrestazione`,`data`),
  KEY `REF_PREST_PREST_IND` (`codTipoPrestazione`),
  CONSTRAINT `REF_PREST_ANIMA` FOREIGN KEY (`codAnimale`) REFERENCES `animale` (`codAnimale`),
  CONSTRAINT `REF_PREST_PREST_FK` FOREIGN KEY (`codTipoPrestazione`) REFERENCES `prestazione` (`codPrestazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestazione_su_animale`
--

LOCK TABLES `prestazione_su_animale` WRITE;
/*!40000 ALTER TABLE `prestazione_su_animale` DISABLE KEYS */;
INSERT INTO `prestazione_su_animale` VALUES (1,5,'2015-02-27','16:00:00',NULL,NULL),(1,5,'2016-02-27','16:30:00',14,4.2),(1,5,'2017-02-27','16:30:00',15,6.2),(1,5,'2018-02-27','16:30:00',15,6.2),(1,5,'2019-02-27','16:30:00',15.5,7.2),(1,5,'2020-02-27','16:30:00',16,6.8),(1,5,'2021-02-27','16:30:00',16.5,9.2),(1,5,'2022-02-09','16:05:00',NULL,NULL),(1,11,'2022-02-05','16:35:00',NULL,NULL),(3,10,'2022-02-08','10:00:00',NULL,NULL),(5,5,'2022-02-08','11:00:00',NULL,NULL),(5,10,'2022-02-09','16:00:00',NULL,NULL),(5,11,'2022-02-07','10:30:00',0,NULL),(6,10,'2022-02-05','17:00:00',62,56),(6,10,'2022-02-07','16:30:00',NULL,NULL),(7,11,'2022-02-05','16:30:00',13.2,16),(7,11,'2022-02-07','16:30:00',30,28.5);
/*!40000 ALTER TABLE `prestazione_su_animale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietario`
--

DROP TABLE IF EXISTS `proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proprietario` (
  `CF` char(16) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `ind_citta` varchar(50) NOT NULL,
  `ind_via` varchar(30) NOT NULL,
  `ind_numero` int NOT NULL,
  `ind_cap` int NOT NULL,
  `telefono` bigint NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `dataDiNascita` date DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `ID_PROPRIETARIO_IND` (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietario`
--

LOCK TABLES `proprietario` WRITE;
/*!40000 ALTER TABLE `proprietario` DISABLE KEYS */;
INSERT INTO `proprietario` VALUES ('CRNLSS80M59H282P','Alessia','Carnevali','Rieti','Via Peucetia',2,2100,3928457770,'alessiacarnevali@gmail.it','1989-12-12'),('DMZPDN80C45D810M','Domiziana','Prudente','Frosinone','Via Pio IX',29,3100,3928458870,'domizianaprudente@gmail.it','1995-12-12'),('MNGMRA80M19D548I','Mario','Mengozzi','Ferrara','Viale degli alberelli',33,29010,3469272820,'mariomengozzi@gmail.com','1990-12-12'),('PLNNCL93B06D458I','Nicola','Paolini','Cesena','Pio IX',2,47521,3425161717,'nicola.paolini@gmail.com','1993-02-06');
/*!40000 ALTER TABLE `proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referto`
--

DROP TABLE IF EXISTS `referto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referto` (
  `codReferto` int NOT NULL AUTO_INCREMENT,
  `codAnimale` int NOT NULL,
  `codTipoPrestazione` int NOT NULL,
  `data` date NOT NULL,
  `descrizione` varchar(600) NOT NULL,
  PRIMARY KEY (`codReferto`),
  UNIQUE KEY `SID_REFER_PREST_ID` (`codAnimale`,`codTipoPrestazione`,`data`),
  UNIQUE KEY `ID_REFERTO_IND` (`codReferto`),
  UNIQUE KEY `SID_REFER_PREST_IND` (`codAnimale`,`codTipoPrestazione`,`data`),
  CONSTRAINT `SID_REFER_PREST_FK` FOREIGN KEY (`codAnimale`, `codTipoPrestazione`, `data`) REFERENCES `prestazione_su_animale` (`codAnimale`, `codTipoPrestazione`, `data`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referto`
--

LOCK TABLES `referto` WRITE;
/*!40000 ALTER TABLE `referto` DISABLE KEYS */;
INSERT INTO `referto` VALUES (1,1,5,'2015-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(13,1,5,'2016-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(14,1,5,'2017-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(15,1,5,'2018-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(16,1,5,'2019-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(17,1,5,'2020-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(18,1,5,'2021-02-27','Effettuato controllo dei parametri dell\'animale. L\'animale risulta in buona salute.'),(19,6,10,'2022-02-05','Riscontrate macchie sulla pancia.'),(20,7,11,'2022-02-05','L\'animale gode di ottima salute.'),(21,7,11,'2022-02-07','Temperatura corporea elevata.'),(22,5,11,'2022-02-07','Riscontrate chiazze sulle zampe');
/*!40000 ALTER TABLE `referto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ricovero`
--

DROP TABLE IF EXISTS `ricovero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ricovero` (
  `codAnimale` int NOT NULL,
  `dataInizio` date NOT NULL,
  `durataInGiorni` char(5) NOT NULL,
  `prezzoGiornaliero` float NOT NULL,
  `numeroPosto` int NOT NULL,
  `oraInizio` time NOT NULL,
  PRIMARY KEY (`codAnimale`,`dataInizio`),
  UNIQUE KEY `ID_RICOVERO_IND` (`codAnimale`,`dataInizio`),
  KEY `REF_RICOV_POSTO_IND` (`numeroPosto`),
  CONSTRAINT `REF_RICOV_ANIMA` FOREIGN KEY (`codAnimale`) REFERENCES `animale` (`codAnimale`),
  CONSTRAINT `REF_RICOV_POSTO_FK` FOREIGN KEY (`numeroPosto`) REFERENCES `posto` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricovero`
--

LOCK TABLES `ricovero` WRITE;
/*!40000 ALTER TABLE `ricovero` DISABLE KEYS */;
INSERT INTO `ricovero` VALUES (5,'2022-02-07','10',30,1,'10:30:00');
/*!40000 ALTER TABLE `ricovero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specializzazione`
--

DROP TABLE IF EXISTS `specializzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specializzazione` (
  `annoConseguimento` date NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `CF` char(16) NOT NULL,
  PRIMARY KEY (`titolo`),
  UNIQUE KEY `ID_SPECIALIZZAZIONE_IND` (`titolo`),
  KEY `REF_SPECI_PERSO_IND` (`CF`),
  CONSTRAINT `REF_SPECI_PERSO_FK` FOREIGN KEY (`CF`) REFERENCES `personale` (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specializzazione`
--

LOCK TABLES `specializzazione` WRITE;
/*!40000 ALTER TABLE `specializzazione` DISABLE KEYS */;
INSERT INTO `specializzazione` VALUES ('2016-02-08','Cura dei Suinetti','uiuiuiuiuiuiuiui');
/*!40000 ALTER TABLE `specializzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strumento`
--

DROP TABLE IF EXISTS `strumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `strumento` (
  `codStrumento` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `descrizione` varchar(350) NOT NULL,
  `dataScadenza` date DEFAULT NULL,
  `quantitaInMagazzino` int NOT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`codStrumento`),
  UNIQUE KEY `ID_STRUMENTO_IND` (`codStrumento`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strumento`
--

LOCK TABLES `strumento` WRITE;
/*!40000 ALTER TABLE `strumento` DISABLE KEYS */;
INSERT INTO `strumento` VALUES (1,'Forbici per iridectomia','11.5 cm di lunghezza',NULL,10,2),(2,'Pinzetta per scheggie','Dotata di una punta particolarmente sottile e acuminata che la rende lo strumento ideale per la rimozione di schegge e altri piccoli corpi estranei.','2024-01-01',10,1),(3,'Forbici per sutura','Le forbici chirurgiche per sutura sono strumenti monouso di alta qualità utilizzati in particolare per la rimozione di tessuti, medicazioni e suture. Forma dritta 13 cm.','2025-01-01',50,2.5),(4,'Forbici  per la pelle','I teli chirurgici Foliodrape Protect, composti da TnT in polipropilene e pellicola in polietilene, sono impermeabili a liquidi e batteri, senza lattice e antistatici.','2025-01-01',150,0.05),(5,'Telo chirurgico','Il telo chirurgico è uno strumento resistente e pratico, realizzato in tessuto non tessuto biaccoppiato ed ha un elevato potere e assorbente. Il telino sterile è un dipositivo monouso ed è impermeabile.','2025-01-01',30,7),(6,'Porta aghi','Grazie al suo formato sottile, il porta aghi è lo strumento ideale per effettuare operazioni di ferite chirurgiche strette e profonde. La struttura in metallo satinato consente poi di evitare i fastidiosi riflessi di luce durante gli interventi chirurgici.','2025-01-01',20,2),(7,'Pinza Emostatica','La pinza emostatica è uno strumento in acciaio inossidabile, disponibile sia nella versione diritta che curva ed è dotato di un dispositivo di bloccaggio grazie al quale può essere fissato nella posizione desiderata. Dimensione 12,5 cm','2022-03-01',50,0.5),(8,'Garza','100% cotone Bianco Bianco Aperto Tessuto di garza nastro. Fasciatura tradizionale non elastica più forte del generale White Open Wove BP in quanto non può essere strappato a mano a causa del suo bordo del nastro e finitura, non lasciando fili di perdita','2026-01-01',200,0.2),(9,'Garza elastica coesiva','Peso leggero, si strappa a mano, impermeabile, facile da applicare a mano e non si stacca, non sono necessari spilli e mollette. Bende auto aderenti realizzate in materiale non tessuto, peso leggero, si strappano a mano, impermeabili, auto aderenti, non appiccicoso.','2026-01-01',90,1.3),(10,'Bisturi','I bisturi monouso sono dotati di una lama dall\'affilatura di precisione realizzata in acciaio di alta qualità sottoposto a speciale tempratura che garantisce tagli perfetti senza sbavature.','2026-01-01',50,0.3),(11,'Guanti','Prodotto biodegradabile che assicura un elevato livello di sensibilità, elasticità ed impermeabilità. Tali proprietà non sono le uniche: i guanti monouso prodotti con lattice naturale vantano una maggiore resistenza a perforazioni e strappi.','2027-01-01',150,0.05),(12,'Siringa','','2025-01-01',99,0.05),(13,'Bisturi lungo','I bisturi lunghi sono dotati di una lama dall\'affilatura di precisione realizzata in acciaio di alta qualità sottoposto a speciale tempratura che garantisce tagli perfetti senza sbavature.','2022-01-15',10,0.5),(14,'Penna chirurgica','Strumento utile per le incisioni chirurgiche.','2023-02-13',10,10),(15,'Curette','Una currette  tagliente è uno strumento chirurgico, di diverse fogge, utilizzato per asportare, scarificare, raschiare o pulire.\n\n','2022-02-10',5,8);
/*!40000 ALTER TABLE `strumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizzo`
--

DROP TABLE IF EXISTS `utilizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizzo` (
  `codStrumento` int NOT NULL,
  `codPrestazione` int NOT NULL,
  `quantita` int NOT NULL,
  PRIMARY KEY (`codStrumento`,`codPrestazione`),
  UNIQUE KEY `ID_utilizzo_IND` (`codStrumento`,`codPrestazione`),
  KEY `REF_utili_PREST_IND` (`codPrestazione`),
  CONSTRAINT `REF_utili_PREST_FK` FOREIGN KEY (`codPrestazione`) REFERENCES `prestazione` (`codPrestazione`),
  CONSTRAINT `REF_utili_STRUM` FOREIGN KEY (`codStrumento`) REFERENCES `strumento` (`codStrumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizzo`
--

LOCK TABLES `utilizzo` WRITE;
/*!40000 ALTER TABLE `utilizzo` DISABLE KEYS */;
INSERT INTO `utilizzo` VALUES (1,2,0),(5,1,1),(5,2,1),(7,2,1),(8,1,5),(8,16,2),(10,1,2),(10,8,2),(13,2,2);
/*!40000 ALTER TABLE `utilizzo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-07 11:26:54


