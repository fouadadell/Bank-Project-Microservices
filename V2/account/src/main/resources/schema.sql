CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` int AUTO_INCREMENT  PRIMARY KEY,
  `account_number` int UNIQUE NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
`account_id` int NOT NULL,
   CONSTRAINT fk_account_id FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)

);

