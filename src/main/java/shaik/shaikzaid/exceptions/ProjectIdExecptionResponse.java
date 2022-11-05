package shaik.shaikzaid.exceptions;

public class ProjectIdExecptionResponse {private String projectIdentifier;
    public ProjectIdExecptionResponse(String projectIdentifier){
        this.projectIdentifier=projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
