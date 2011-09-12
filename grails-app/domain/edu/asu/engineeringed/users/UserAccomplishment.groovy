package edu.asu.engineeringed.users

import org.apache.commons.lang.builder.HashCodeBuilder

class UserAccomplishment implements Serializable {

	User user
	Accomplishment accomplishment

	boolean equals(other) {
		if (!(other instanceof UserAccomplishment)) {
			return false
		}

		other.user?.id == user?.id &&
			other.accomplishment?.id == accomplishment?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (accomplishment) builder.append(accomplishment.id)
		builder.toHashCode()
	}

	static UserAccomplishment get(long userId, long accomplishmentId) {
		find 'from UserAccomplishment where user.id=:userId and accomplishment.id=:accomplishmentId',
			[userId: userId, accomplishmentId: accomplishmentId]
	}

	static UserAccomplishment create(User user, Accomplishment accomplishment, boolean flush = false) {
		new UserAccomplishment(user: user, Accomplishment: accomplishment).save(flush: flush, insert: true)
	}

	static boolean remove(User user, Accomplishment accomplishment, boolean flush = false) {
		UserAccomplishment instance = UserAccomplishment.findByUserAndAccomplishment(user, accomplishment)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserAccomplishment WHERE user=:user', [user: user]
	}

	static void removeAll(Accomplishment accomplishment) {
		executeUpdate 'DELETE FROM UserAccomplishment WHERE accomplishment=:accomplishment', [accomplishment: accomplishment]
	}

	static mapping = {
		id composite: ['accomplishment', 'user']
		version false
	}
}
