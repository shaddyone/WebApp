package in.co.lib.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BookBean;
import in.co.lib.mgt.bean.UserBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.exception.DatabaseException;
import in.co.lib.mgt.exception.DuplicateRecordException;
import in.co.lib.mgt.util.JDBCDataSource;

public class BookModel {

	private static Logger log = Logger.getLogger(UserModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM LI_BOOK");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextBookCode() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(BookCode) FROM LI_Book");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(BookBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		BookBean existbean = findByBookName(bean.getBookName());

		if (existbean != null) {
			throw new DuplicateRecordException("Book Is already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO LI_BOOK VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, nextBookCode());
			pstmt.setString(3, bean.getBookName());
			pstmt.setString(4, bean.getWriterName());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public BookBean findByBookName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_Book WHERE BookName=?");
		BookBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBookCode(rs.getLong(2));
				bean.setBookName(rs.getString(3));
				bean.setWriterName(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public BookBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_Book WHERE ID=?");
		BookBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBookCode(rs.getLong(2));
				bean.setBookName(rs.getString(3));
				bean.setWriterName(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public BookBean findByBookCode(long code) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_BOOK WHERE BOOKCODE=?");
		BookBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBookCode(rs.getLong(2));
				bean.setBookName(rs.getString(3));
				bean.setWriterName(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public void delete(BookBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM LI_BOOK WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	 public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	    /**
	     * Get List of Role with pagination
	     * 
	     * @return list : List of Role
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List list(int pageNo, int pageSize) throws ApplicationException {
	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from LI_BOOK");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                BookBean bean = new BookBean();
	                bean.setId(rs.getLong(1));
					bean.setBookCode(rs.getLong(2));
					bean.setBookName(rs.getString(3));
					bean.setWriterName(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model list End");
	        return list;

	    }
	    
	    public List search(BookBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	    /**
	     * Search Role with pagination
	     * 
	     * @return list : List of Roles
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * 
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List search(BookBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM LI_BOOK WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getBookCode() > 0) {
	                sql.append(" AND BookCode = " + bean.getBookCode());
	            }
	            if (bean.getBookName() != null && bean.getBookName().length() > 0) {
					sql.append(" AND BOOKNAME LIKE '" + bean.getBookName() + "%'");
	            }
	            if (bean.getWriterName() != null
	                    && bean.getWriterName().length() > 0) {
					sql.append(" AND WriterName LIKE '" + bean.getWriterName()
	                        + "%'");
	            }
	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new BookBean();
	                bean.setId(rs.getLong(1));
					bean.setBookCode(rs.getLong(2));
					bean.setBookName(rs.getString(3));
					bean.setWriterName(rs.getString(4));
					bean.setCreatedBy(rs.getString(5));
					bean.setModifiedBy(rs.getString(6));
					bean.setCreatedDatetime(rs.getTimestamp(7));
					bean.setModifiedDatetime(rs.getTimestamp(8));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model search End");
	        return list;
	    }
	    
	    public void update(BookBean bean) throws ApplicationException, DuplicateRecordException {
	    	log.debug("Model update Started");
	    	Connection conn = null;

	    	BookBean beanExist = findByBookName(bean.getBookName());
	    	// Check if updated LoginId already exist
	    	if (beanExist != null && !(beanExist.getId() == bean.getId())) {
	    		throw new DuplicateRecordException("BookBean is already exist");
	    	}

	    	try {
	    		conn = JDBCDataSource.getConnection();
	    		conn.setAutoCommit(false); // Begin transaction
	    		PreparedStatement pstmt = conn.prepareStatement(
	    				"UPDATE LI_BOOK SET BOOKCODE=?,BOOKNAME=?,WRITERNAME=?,"
	    				+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
	    		pstmt.setLong(1, bean.getBookCode());
				pstmt.setString(2, bean.getBookName());
				pstmt.setString(3, bean.getWriterName());
				pstmt.setString(4, bean.getCreatedBy());
				pstmt.setString(5, bean.getModifiedBy());
				pstmt.setTimestamp(6, bean.getCreatedDatetime());
				pstmt.setTimestamp(7, bean.getModifiedDatetime());
	    		pstmt.setLong(8,bean.getId());
	    		pstmt.executeUpdate();
	    		conn.commit(); // End transaction
	    		pstmt.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		log.error("Database Exception..", e);
	    		e.printStackTrace();
	    		try {
	    			conn.rollback();
	    		} catch (Exception ex) {
	    			throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
	    		}
	    		throw new ApplicationException("Exception in updating User ");
	    	} finally {
	    		JDBCDataSource.closeConnection(conn);
	    	}
	    	log.debug("Model update End");
	    }
	
	

}
