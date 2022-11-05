package shaik.shaikzaid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shaik.shaikzaid.domain.Backlog;
import shaik.shaikzaid.domain.Project;
import shaik.shaikzaid.domain.ProjectTask;
import shaik.shaikzaid.exceptions.ProjectNotFoundException;
import shaik.shaikzaid.repositories.BacklogRepository;
import shaik.shaikzaid.repositories.ProjectRepository;
import shaik.shaikzaid.repositories.ProjectTaskRepository;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask){
        try{
            Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);
            Integer BacklogSequence=backlog.getPTSequence();

            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);

            projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if( projectTask.getPriority()==null){
                projectTask.setPriority(3);
            }
            if (projectTask.getStatus() == "" || projectTask.getStatus()==null){
                projectTask.setStatus("TO_DD");
            }
            return projectTaskRepository.save(projectTask);
        } catch (Exception e){
            throw new ProjectNotFoundException("Project Not Found");
        }
    }


   public Iterable<ProjectTask> findBacklogById(String id) {
        Project project=projectRepository.findByProjectIdentifier(id);

        if(project == null){
            throw new ProjectNotFoundException("Project with Id:"+id+"doesnt exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
    public ProjectTask findPTByProjectSequence(String backlog_id,String sequence){
        return projectTaskRepository.findByProjectSequence(sequence);
    }
}
