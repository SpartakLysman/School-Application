package ua.com.foxminded.javaspring.SchoolApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Entity<K extends Comparable<K>> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "key")
	private K key;

	public Entity(K key) {
		this.key = key;
	}

	public Entity() {

	}

	public K getKey() {
		return key;
	}

	public void setKey(K newKey) {
		this.key = newKey;
	}

	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (this.getClass() != object.getClass()) {
			return false;
		}

		Entity<?> entity = (Entity<?>) object;
		return entity.key.equals(key);
	}

	public int hashCode() {

		int number = 5;
		number = number * 5 + key.hashCode();
		return number;
	}

	public String toString() {
		return "Id: " + this.key;
	}
}
