package cl.acn.lab.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.acn.lab.demo.entity.Video;

@Repository("videoRepository")
public interface VideoRepository extends JpaRepository<Video, Long>{
	
	@Query(value = "SELECT * FROM VIDEO", nativeQuery = true)
	public List<Video> getAll();

}

