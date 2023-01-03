package in.co.lib.mgt.bean;

import java.util.Date;

public class IssuBookBean extends BaseBean {
	
	
	private String bookName;
	private String writerName;
	private Date issuDate;
	private Date returnDate;
	private long bookCode;
	private String studenName;
	private long studentLibCode;
	
	
	
	
	

	public String getStudenName() {
		return studenName;
	}

	public void setStudenName(String studenName) {
		this.studenName = studenName;
	}

	public long getStudentLibCode() {
		return studentLibCode;
	}

	public void setStudentLibCode(long studentLibCode) {
		this.studentLibCode = studentLibCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public Date getIssuDate() {
		return issuDate;
	}

	public void setIssuDate(Date issuDate) {
		this.issuDate = issuDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public long getBookCode() {
		return bookCode;
	}

	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
