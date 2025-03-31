-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Mar 31, 2025 at 07:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tasks`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `task_description` text DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `task_status` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `task_name`, `task_description`, `due_date`, `task_status`, `created_at`, `updated_at`) VALUES
(2, 'skaks', 'skaksk', '2002-12-12', 'Completed', '2025-03-27 15:01:29', '2025-03-27 15:01:29'),
(3, 'czxzz', 'zxzzx', '2001-03-04', 'Completed', '2025-03-27 15:03:29', '2025-03-27 15:03:29'),
(5, 'd', 'a', '2025-12-12', 'Completed', '2025-03-27 15:07:10', '2025-03-27 15:07:10'),
(6, 'sdde', 'ssda', '2023-04-03', 'Completed', '2025-03-27 15:10:07', '2025-03-27 15:10:07'),
(18, 'ass', 'dfge', '1212-10-10', 'Completed', '2025-03-31 12:34:30', '2025-03-31 12:35:46'),
(19, 'b', 'd', '2022-10-12', 'Completed', '2025-03-31 13:00:21', '2025-03-31 13:00:37'),
(20, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:03:22', '2025-03-31 17:15:52'),
(21, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:05:54', '2025-03-31 17:15:52'),
(22, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:09:03', '2025-03-31 17:15:52'),
(23, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:10:46', '2025-03-31 17:15:52'),
(24, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:12:29', '2025-03-31 17:15:52'),
(25, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:15:04', '2025-03-31 17:15:52'),
(26, 'Updated Task', 'Updated Description', '2024-01-01', 'Completed', '2025-03-31 17:15:50', '2025-03-31 17:15:52');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
