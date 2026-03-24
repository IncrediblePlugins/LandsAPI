package me.angeschossen.lands.api.membershiprequest;

import java.sql.Timestamp;

/**
 * Represents a request for membership.
 */
public interface MemberShipRequest {
    /**
     * Get the time when this request was sent.
     *
     * @return Time when this request was sent
     */
    Timestamp getTime();
}
