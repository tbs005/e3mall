package cn.e3mall.search.service.impl;


import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;

/**
 * solr索引库导入
 * @author 吾霞
 *
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	private ItemMapper mapper;
	
	@Autowired
	private SolrServer solrServer;
	
	public E3Result importItem() {
		try {
			//查询商品列表
			List<SearchItem> list = mapper.getItemList();
			//导入索引库
			for (SearchItem searchItem : list) {
				//创建文档对象
				SolrInputDocument document=new SolrInputDocument();
				//向文档中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			return E3Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500,"商品导入失败");
		}
	}

}
