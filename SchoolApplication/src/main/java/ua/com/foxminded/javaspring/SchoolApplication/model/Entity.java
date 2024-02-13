package ua.com.foxminded.javaspring.SchoolApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Entity<K extends Comparable<K>> {

	@Id
	@Column(name = "key")
	private K key;

	public Entity(K key) {
		this.key = key;
	}

	public Entity() {

	}

	public String toString() {
		return "Id: " + this.key;
	}
}
