package softuni.exam.domain.entities.dtos.jsons;

import com.google.gson.annotations.Expose;

public class TeamJsonDto {

    @Expose
    private String name;
    @Expose
    private PictureJsonDto pictureJsonDto;

    public TeamJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureJsonDto getPictureJsonDto() {
        return pictureJsonDto;
    }

    public void setPictureJsonDto(PictureJsonDto pictureJsonDto) {
        this.pictureJsonDto = pictureJsonDto;
    }
}
