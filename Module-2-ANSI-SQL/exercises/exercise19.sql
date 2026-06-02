SELECT
    e.event_id,
    e.title,
    (
        SELECT COUNT(*)
        FROM Registrations r
        WHERE r.event_id = e.event_id
    ) AS total_registrations,
    (
        SELECT AVG(f.rating)
        FROM Feedback f
        WHERE f.event_id = e.event_id
    ) AS average_rating
FROM Events e
WHERE e.status = 'completed';