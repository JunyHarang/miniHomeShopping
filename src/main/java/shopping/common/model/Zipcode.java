package shopping.common.model ;

public class Zipcode {
	private String zipcode;	//�����ȣ�� ������ ����
	private String sido; //���� �Ǵ� ���� ������ ����
	private String gugun; //�� �Ǵ� �ҵ��ø� ������ ����
	private String dong; //�� �Ǵ� ��,���� ������ ����
	private String bunji; //�ּ��� ������ ������ ����
	private int seqnum ;
	
	public void setZipcode(String zipcode){ this.zipcode=zipcode;	}
	public void setSido(String sido){ this.sido=sido; 	}
	public void setGugun(String gugun){ this.gugun=gugun; 	}
	public void setDong(String dong){ this.dong=dong; 	}
	public void setBunji(String bunji){ this.bunji=bunji; 	}
	public String getZipcode(){ return zipcode; }
	public String getSido(){ return sido; }
	public String getGugun(){ 	return gugun; }
	public String getDong(){ 	return dong; 	}
	public String getBunji(){ 	return bunji;  	}
	public int getSeqnum() {return seqnum;}
	public void setSeqnum(int seqnum) { this.seqnum = seqnum;}	
}