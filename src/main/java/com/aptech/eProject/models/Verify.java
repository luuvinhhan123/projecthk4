package com.aptech.eProject.models;

import com.aptech.eProject.types.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_verifications")
@SQLDelete(sql = "UPDATE tbl_verifications SET deleted_at = CURRENT_TIMESTAMP, modified_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Verify extends Model {
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "code")
	private String code;

	@Column(name = "expired_at")
	private Date expiredAt;

	public Verify(String email) {
		super();
		this.email = email;
		this.code = this.generateVerifiedCode();
		this.expiredAt = this.getTokenExpirationTime();
	}

	public Verify(String email, Long userId) {
		super();
		this.userId = userId;
		this.email = email;
		this.code = this.generateVerifiedCode();
		this.expiredAt = this.getTokenExpirationTime();
	}

	/**
	 * Generate token expired
	 * @return
	 */
	public Date getTokenExpirationTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE,30);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * Random 6 digits
	 * @return String
	 */
	public String generateVerifiedCode() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			int digit = random.nextInt(10);
			stringBuilder.append(digit);
		}

		return stringBuilder.toString();
	}
}
