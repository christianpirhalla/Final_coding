package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class RateDAL {


	public static double getRate(int GivenCreditScore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel rateGet = null;		
		double rate = 0;
		
		try {
			tx = session.beginTransaction();	
			
			Query query = session.createQuery("from TblRate where MinCreditScore = :GivenCreditScore ");
			query.setParameter("GivenCreditScore", GivenCreditScore);

			List<?> list = query.list();
			rateGet = (RateDomainModel)list.get(0);
			rate = rateGet.getInterestRate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return rate;
	}		

	}

