package softuni.exam.domain.entities.dtos.jsons;

import com.google.gson.annotations.Expose;

public class PictureJsonDto {

    @Expose
    private String url;

    public PictureJsonDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
