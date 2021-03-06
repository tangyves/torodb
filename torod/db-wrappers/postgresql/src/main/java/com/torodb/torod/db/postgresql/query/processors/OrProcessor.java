/*
 *     This file is part of ToroDB.
 *
 *     ToroDB is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ToroDB is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with ToroDB. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */

package com.torodb.torod.db.postgresql.query.processors;

import com.torodb.torod.db.postgresql.query.ProcessedQueryCriteria;
import com.google.common.collect.Lists;
import com.torodb.torod.core.language.querycriteria.OrQueryCriteria;
import com.torodb.torod.core.language.querycriteria.utils.QueryCriteriaVisitor;
import java.util.List;

/**
 *
 */
public class OrProcessor {

    public static List<ProcessedQueryCriteria> process(
            OrQueryCriteria criteria, 
            QueryCriteriaVisitor<List<ProcessedQueryCriteria>, Void> visitor) {
        
        List<ProcessedQueryCriteria> child1Result = criteria.getSubQueryCriteria1().accept(visitor, null);
        List<ProcessedQueryCriteria> child2Result = criteria.getSubQueryCriteria2().accept(visitor, null);

        List<ProcessedQueryCriteria> result = Lists.newArrayListWithCapacity(child1Result.size() + child2Result.size());
        result.addAll(child1Result);
        result.addAll(child2Result);

        return result;

    }

}
