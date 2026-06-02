SELECT
    u.city,
    COUNT(DISTINCT r.user_id) AS registration_count
FROM Users u
JOIN Registrations r
    ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY registration_count DESC
LIMIT 5;