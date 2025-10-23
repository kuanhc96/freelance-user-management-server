--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `user_guid` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `password` varchar(500) NOT NULL,
                         `ROLE` varchar(100) NOT NULL DEFAULT 'student',
                         `status` varchar(100) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `gender` varchar(100) DEFAULT NULL,
                         `description` text,
                         `birthday` datetime DEFAULT NULL,
                         `profile_picture` varchar(255) DEFAULT NULL,
                         `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `user_guid` (`user_guid`),
                         UNIQUE KEY `unique_email_role` (`email`,`ROLE`)
) /*! ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci */;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

-- LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'36946828-6696-4b83-be33-6093f2efef70','kuandalice@gmail.com','{bcrypt}$2a$10$cbNuVCFlntaTR6Ugvr1lIu790hHSNnNof9/fAcFcj0G7I/L9ulpUS','INSTRUCTOR','CREATED','Alice Ho','female','The best golf instructor in Taiwan!','1996-08-05 00:00:00','/alice.jpg','2025-04-23 16:10:00','2025-06-10 13:37:39'),(25,'edf7e0b4-55bf-4302-8685-c546a78a05e9','kuantest@example.com','{bcrypt}$2a$10$SR0z9KozjW.9hP/VxSZv9eP.u50.buh0ROsUtYbsdYXRDYTumj0fG','STUDENT','CREATED','few342','MALE','lol','2025-04-23 00:00:00',NULL,'2025-04-23 17:26:35','2025-06-10 13:38:37'),(26,'d0fa80c4-9bbe-4b10-acf3-0f00e6bed65a','kuantest23@example.com','{bcrypt}$2a$10$vkHO5vbvYRazKc1vauoJXOUvDUSUg2zy9BznlfGhCLLH1sbv9aNyy','STUDENT','CREATED','Kuantest','MALE','fdfawe','2025-04-23 00:00:00',NULL,'2025-04-23 17:29:44','2025-06-10 13:38:37'),(27,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','kuantest1234@example.com','{bcrypt}$2a$10$Q/.eryryfRr0k1Y.NO8JpOZ56Q16ZO4hl5LJoUkT1mxStB4C5CaNS','STUDENT','CREATED','Kuantest','MALE','fdaerwq','2025-05-12 00:00:00','/favicon.ico','2025-05-12 11:47:40','2025-06-10 13:38:37'),(28,'aa02e645-55ea-4aa3-953e-3ea543c8290f','kuantest1234@example.com','{bcrypt}$2a$10$yXfB6jT58O1tIEQuQHMgO.ew1C94JajaYP6PjocSTAkcni48Xk09S','INSTRUCTOR','CREATED','Kuan Chen','MALE','account used for testing','1996-11-05 00:00:00',NULL,'2025-06-13 13:57:31','2025-06-13 13:57:31');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
-- UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

