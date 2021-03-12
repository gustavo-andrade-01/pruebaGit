package cl.acn.lab.demo.service;

import java.util.List;

import cl.acn.lab.demo.dto.VideoDTO;

public interface VideoService {

	 public List<VideoDTO> getAll();
	 
	 public boolean save(List<VideoDTO> input);
}
