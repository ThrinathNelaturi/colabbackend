package com.niit.project2B.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.project2B.DAO.BlogDAO;
import com.niit.project2B.DAO.BlogLikesDAO;
import com.niit.project2B.DAO.EventDAO;
import com.niit.project2B.DAO.ForumCommentDAO;
import com.niit.project2B.DAO.ForumDAO;
import com.niit.project2B.DAO.FriendDAO;
import com.niit.project2B.DAO.JobDAO;
import com.niit.project2B.DAO.UserDAO;
import com.niit.project2B.DAOImpl.BlogDAOImpl;
import com.niit.project2B.DAOImpl.BlogLikesDAOImpl;
import com.niit.project2B.DAOImpl.EventDAOImpl;
import com.niit.project2B.DAOImpl.ForumCommentDAOImpl;
import com.niit.project2B.DAOImpl.ForumDAOImpl;
import com.niit.project2B.DAOImpl.FriendDAOImpl;
import com.niit.project2B.DAOImpl.JobDAOImpl;
import com.niit.project2B.DAOImpl.UserDAOImpl;
import com.niit.project2B.model.Blog;
import com.niit.project2B.model.BlogLikes;
import com.niit.project2B.model.Event;
import com.niit.project2B.model.Forum;
import com.niit.project2B.model.ForumComment;
import com.niit.project2B.model.Friend;
import com.niit.project2B.model.Job;
import com.niit.project2B.model.User;


@Configuration
@ComponentScan("com.niit.project2B")
@EnableTransactionManagement
public class ApplicationContextConfig 
{

		@Bean(name = "dataSource")
		public DataSource getDataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
			dataSource.setUsername("thrinath");
			dataSource.setPassword("nelaturi");
			System.out.println("Datasource");
			return dataSource;

		}

		private Properties getHibernateProperties() {
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
			properties.put("hibernate.hbm2ddl.auto", "update");
			System.out.println("Hibernate Properties");
			return properties;

		}

		@Autowired
		@Bean(name = "sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource) {
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.addAnnotatedClass(User.class);
			sessionBuilder.addAnnotatedClass(Friend.class);
			sessionBuilder.addAnnotatedClass(Blog.class);
			sessionBuilder.addAnnotatedClass(BlogLikes.class);
			sessionBuilder.addAnnotatedClass(Event.class);
			sessionBuilder.addAnnotatedClass(Forum.class);
			sessionBuilder.addAnnotatedClass(Job.class);
			sessionBuilder.addAnnotatedClass(ForumComment.class);
			
	
		
			System.out.println("Session");
			
			return sessionBuilder.buildSessionFactory();
			
		}

		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			System.out.println("Transaction");
			return transactionManager;
		}
		
		@Autowired
		@Bean(name = "userDAO")
		public UserDAO getUserDao(SessionFactory sessionFactory) {
			System.out.println("user is done");
				return new UserDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name = "friendDAO")
		public FriendDAO getFriendDao(SessionFactory sessionFactory) {
			System.out.println("friend is done");
				return new FriendDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="blogDAO")
		public BlogDAO getBlogDAO(SessionFactory sessionFactory){
			
			return new BlogDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="blogLikesDAO")
		public BlogLikesDAO getBlogLikesDAO(SessionFactory sessionFactory){
			return new BlogLikesDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="eventDAO")
		public EventDAO getEventDAO(SessionFactory sessionFactory){
			
			return new EventDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="forumDAO")
		public ForumDAO getForumDAO(SessionFactory sessionFactory){
			System.out.println("forum is done");
			return new ForumDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="jobDAO")
		public JobDAO getJobDAO(SessionFactory sessionFactory){
			
			return new JobDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name="forumCommentDAO")
		public ForumCommentDAO getForumCommentDAO(SessionFactory sessionFactory){
			return new ForumCommentDAOImpl(sessionFactory);
		}
}
