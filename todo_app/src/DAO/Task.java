package DAO;

import java.sql.Timestamp;

public record Task(int id, String title, String description, Timestamp deadline, int priority, Boolean done) {}
