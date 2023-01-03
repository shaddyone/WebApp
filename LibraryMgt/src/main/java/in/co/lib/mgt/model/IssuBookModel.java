package in.co.lib.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BookBean;
import in.co.lib.mgt.bean.IssuBookBean;
import in.co.lib.mgt.bean.UserBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.exception.DatabaseException;
import in.co.lib.mgt.exception.DuplicateRecordException;
import in.co.lib.mgt.util.JDBCDataSource;

public class IssuBookModel {
	
	private static Logger log = Logger.getLogger(IssuBookModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM LI_ISSUBOOK");
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
	
	public long add(IssuBookBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByLibCode(bean.getStudentLibCode());
		bean.setStudenName(uBean.getFirstName()+" "+uBean.getLastName());
		
		BookModel bModel=new BookModel();
		BookBean bBean=bModel.findByBookCode(bean.getBookCode());
		bean.setBookName(bBean.getBookName());
		bean.setWriterName(bBean.getWriterName());

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO LI_IssuBook VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,bean.getStudentLibCode());
			pstmt.setString(3,bean.getStudenName());
			pstmt.setString(4, bean.getBookName());
			pstmt.setString(5, bean.getWriterName());
			pstmt.setDate(6,new java.sql.Date(bean.getIssuDate().getTime()));
			pstmt.setDate(7,new java.sql.Date(bean.getReturnDate().getTime()));
			pstmt.setLong(8,bean.getBookCode());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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
	
	public IssuBookBean findByBookName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_ISSUBook WHERE BookName=?");
		IssuBookBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new IssuBookBean();
				bean.setId(rs.getLong(1));
				bean.setStudentLibCode(rs.getLong(2));
				bean.setStudenName(rs.getString(3));
				bean.setBookName(rs.getString(4));
				bean.setWriterName(rs.getString(5));
				bean.setIssuDate(rs.getDate(6));
				bean.setReturnDate(rs.getDate(7));
				bean.setBookCode(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
	
	
	
	public IssuBookBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_ISSUBOOK WHERE ID=?");
		IssuBookBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new IssuBookBean();
				bean.setId(rs.getLong(1));
				bean.setStudentLibCode(rs.getLong(2));
				bean.setStudenName(rs.getString(3));
				bean.setBookName(rs.getString(4));
				bean.setWriterName(rs.getString(5));
				bean.setIssuDate(rs.getDate(6));
				bean.setReturnDate(rs.getDate(7));
				bean.setBookCode(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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
	
	public IssuBookBean findByBookCode(long code) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM LI_ISSUBOOK WHERE bookCode=?");
		IssuBookBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new IssuBookBean();
				bean.setId(rs.getLong(1));
				bean.setStudentLibCode(rs.getLong(2));
				bean.setStudenName(rs.getString(3));
				bean.setBookName(rs.getString(4));
				bean.setWriterName(rs.getString(5));
				bean.setIssuDate(rs.getDate(6));
				bean.setReturnDate(rs.getDate(7));
				bean.setBookCode(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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
	
	public void delete(IssuBookBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM LI_IssuBook WHERE ID=?");
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
        StringBuffer sql = new StringBuffer("select * from LI_ISSUBOOK");
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
                IssuBookBean bean = new IssuBookBean();
                bean.setId(rs.getLong(1));
				bean.setStudentLibCode(rs.getLong(2));
				bean.setStudenName(rs.getString(3));
				bean.setBookName(rs.getString(4));
				bean.setWriterName(rs.getString(5));
				bean.setIssuDate(rs.getDate(6));
				bean.setReturnDate(rs.getDate(7));
				bean.setBookCode(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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
    
    public List search(IssuBookBean bean) throws ApplicationException {
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
    public List search(IssuBookBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM LI_IssuBook WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getBookCode() > 0) {
                sql.append(" AND BookCode = " + bean.getBookCode());
            }
            if (bean.getStudentLibCode() > 0) {
                sql.append(" AND StudentLibCode = " + bean.getStudentLibCode());
            }
            if (bean.getBookName() != null && bean.getBookName().length() > 0) {
				sql.append(" AND BOOKNAME LIKE '" + bean.getBookName() + "%'");
            }
            if (bean.getStudenName() != null && bean.getStudenName().length() > 0) {
				sql.append(" AND StudentName LIKE '" + bean.getStudenName() + "%'");
            }
            
            if (bean.getIssuDate() != null) {
				sql.append(" AND issudate LIKE '" + new java.sql.Date(bean.getIssuDate().getTime()) + "%'");
            }
            if (bean.getWriterName() != null
                    && bean.getWriterName().length() > 0) {
				sql.append(" AND WriterName LIKE '" + bean.getWriterName()
                        + "%'");
            }
        }
        
        sql.append(" Order by ID Desc");

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
            System.out.println(sql.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new IssuBookBean();
                bean.setId(rs.getLong(1));
				bean.setStudentLibCode(rs.getLong(2));
				bean.setStudenName(rs.getString(3));
				bean.setBookName(rs.getString(4));
				bean.setWriterName(rs.getString(5));
				bean.setIssuDate(rs.getDate(6));
				bean.setReturnDate(rs.getDate(7));
				bean.setBookCode(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
    public static void main(String[] args) throws ApplicationException {
    	
    	IssuBookModel model=new IssuBookModel(); 
    	IssuBookBean bean=null;
    	model.search(bean);
    	
 		
	}
	
	

}
