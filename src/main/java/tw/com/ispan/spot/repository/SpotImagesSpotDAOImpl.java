package tw.com.ispan.spot.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.spot.domain.SpotImagesSpot;

public class SpotImagesSpotDAOImpl implements SpotImagesSpotDAO {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public long countSpotImagesSpot(JSONObject param) {
		// SELECT count(*) FROM SpotImagesSpot
		// WHERE CategoryId = ? or SpotTitle like '%?%' or SpotDescription like '%?%';

		Integer categoryId = param.isNull("categoryId") ? null : param.getInt("categoryId");
		// String spotTitle = param.isNull("spotTitle") ? null :
		// param.getString("spotTitle");
		// String spotDescription = param.isNull("spotDescription") ? null :
		// param.getString("spotDescription");
		String keyword = param.isNull("keyword") ? null : param.getString("keyword");

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);

		// from SpotImagesSpot
		Root<SpotImagesSpot> table = query.from(SpotImagesSpot.class);

		// select count(*)
		query = query.select(builder.count(table));

		List<Predicate> predicates = new ArrayList<>();

		// CategoryId = ?
		if (categoryId != null && categoryId != 0) {
			predicates.add(builder.equal(table.get("categoryId"), categoryId));
		}

		// // spotTitle like '%?%'
		// if (spotTitle != null && spotTitle.length() != 0) {
		// predicates.add(builder.like(table.get("spotTitle"), "%" + spotTitle + "%"));
		// }

		// // spotDescription like '%?%'
		// if (spotDescription != null && spotDescription.length() != 0) {
		// predicates.add(builder.like(table.get("spotDescription"), "%" +
		// spotDescription + "%"));
		// }
		if (keyword != null && keyword.length() != 0) {
			predicates.add(builder.like(table.get("spotTitle"), "%" + keyword + "%"));
			predicates.add(builder.like(table.get("spotDescription"), "%" + keyword + "%"));
		}

		// where CategoryId = ? or spotTitle like '%?%' or spotDescription like '%?%'
		if (predicates != null && !predicates.isEmpty()) {
			query = query.where(builder.or(predicates.toArray(new Predicate[0])));
		}

		TypedQuery<Long> typedQuery = this.getSession().createQuery(query);
		Long result = typedQuery.getSingleResult();
		if (result != null) {
			return result;
		} else {
			return 0;
		}
	}

	@Override
	public List<SpotImagesSpot> findSpotImagesSpot(JSONObject param) {
		// SELECT * FROM SpotImagesSpot
		// WHERE CategoryId = ? or SpotTitle like '%?%' or SpotDescription like '%?%'
		// ORDER BY ??? desc

		Integer categoryId = param.isNull("categoryId") ? null : param.getInt("categoryId");
		// String spotTitle = param.isNull("spotTitle") ? null :
		// param.getString("spotTitle");
		// String spotDescription = param.isNull("spotDescription") ? null :
		// param.getString("spotDescription");
		String keyword = param.isNull("keyword") ? null : param.getString("keyword");

		int start = param.isNull("start") ? 0 : param.getInt("start");
		int rows = param.isNull("rows") ? 10 : param.getInt("rows");
		boolean dir = param.isNull("dir") ? false : param.getBoolean("dir");
		String sort = param.isNull("sort") ? "spotId" : param.getString("sort");

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<SpotImagesSpot> query = builder.createQuery(SpotImagesSpot.class);

		// from SpotImagesSpot
		Root<SpotImagesSpot> table = query.from(SpotImagesSpot.class);

		List<Predicate> predicates = new ArrayList<>();

		// CategoryId = ?
		if (categoryId != null && categoryId != 0) {
			predicates.add(builder.equal(table.get("categoryId"), categoryId));
		}

		// // spotTitle like '%?%'
		// if (spotTitle != null && spotTitle.length() != 0) {
		// predicates.add(builder.like(table.get("spotTitle"), "%" + spotTitle + "%"));
		// }
		if (keyword != null && keyword.length() != 0) {
			predicates.add(builder.like(table.get("spotTitle"), "%" + keyword + "%"));
			predicates.add(builder.like(table.get("spotDescription"), "%" + keyword + "%"));
		}

		// // spotDescription like '%?%'
		// if (spotDescription != null && spotDescription.length() != 0) {
		// predicates.add(builder.like(table.get("spotDescription"), "%" +
		// spotDescription + "%"));
		// }

		// where CategoryId = ? or spotTitle like '%?%' or spotDescription like '%?%'
		if (predicates != null && !predicates.isEmpty()) {
			query = query.where(builder.or(predicates.toArray(new Predicate[0])));
		}

		if (dir) {
			// ORDER BY ??? desc
			query = query.orderBy(builder.desc(table.get(sort)));
		} else {
			// ORDER BY ??? asc
			query = query.orderBy(builder.asc(table.get(sort)));
		}

		TypedQuery<SpotImagesSpot> typedQuery = this.getSession().createQuery(query)
				.setFirstResult(start)
				.setMaxResults(rows);

		List<SpotImagesSpot> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
}
