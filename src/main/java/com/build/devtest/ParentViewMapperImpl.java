package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

public class ParentViewMapperImpl implements ParentViewMapper {
	public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows){
		List<ParentView> parentViews = new ArrayList<ParentView>();

		// for each childRow: 
		// 1. Create or find a parentView corresponding to it
		// 2. Create the ChildView list for all the parentView object
		for (ChildRow childRow: childRows)
		{
			String pid = childRow.getParentId();
			ParentView hasParentView = null; // used to check the parentView whether it is created 
			for(ParentView parentView: parentViews)
			{
				if(pid.equals(parentView.getParentId().toUpperCase()))
				{
					hasParentView = parentView; // if parentView exists in the parentViews list
					hasParentView.getChildViews().add(new ChildView(childRow.getFirstName(), childRow.getLastName(),
							childRow.getAge(), childRow.getChildId(), hasParentView)); // add the childView to the parentView object
					break;
				}
			}

			// if there is not such a parentView in the list, then create one and add it to the list
			if(hasParentView == null)
			{
				for(ParentRow parentRow: parentRows)
				{
					if (pid.equals(parentRow.getParentId().toUpperCase()))
					{
						hasParentView = new ParentView(parentRow.getFirstName(), parentRow.getLastName(),parentRow.getAge(),
								parentRow.getParentId(),new ArrayList<ChildView>());
						hasParentView.getChildViews().add(new ChildView(childRow.getFirstName(), childRow.getLastName(),
								childRow.getAge(), childRow.getChildId(), hasParentView));// add the childView to the parentView object
						
						parentViews.add(hasParentView);
					}
				}
			}			
		}
		return parentViews;
	}
}
