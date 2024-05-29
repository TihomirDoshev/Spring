package softuni.exam.models.dto.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordRootDto {
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordDto>borrowingRecordDto;

    public List<BorrowingRecordDto> getBorrowingRecordDto() {
        return borrowingRecordDto;
    }

    public void setBorrowingRecordDto(List<BorrowingRecordDto> borrowingRecordDto) {
        this.borrowingRecordDto = borrowingRecordDto;
    }

    public BorrowingRecordRootDto() {

    }
    }


