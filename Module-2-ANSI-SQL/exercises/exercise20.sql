SELECT
    u.user_id,
    u.full_name,
    (
        SELECT COUNT(*)
        FROM Registrations r
        WHERE r.user_id = u.user_id
    ) AS events_attended,
    (
        SELECT COUNT(*)
        FROM Feedback f
        WHERE f.user_id = u.user_id
    ) AS feedbacks_submitted
FROM Users u
ORDER BY u.user_id;