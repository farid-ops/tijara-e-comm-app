package amiral.aokiji.tijara;

import amiral.aokiji.tijara.core.model.TagEntity;
import amiral.aokiji.tijara.core.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class TijaraApplication implements CommandLineRunner {

    @Autowired
    private TagRepository tagRepository;

    public static void main(String[] args) {
        SpringApplication.run(TijaraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TagEntity tagEntity = new TagEntity(UUID.randomUUID(),"tagname");
        this.tagRepository.save(tagEntity);
    }
}
